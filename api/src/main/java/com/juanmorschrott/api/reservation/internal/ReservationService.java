package com.juanmorschrott.api.reservation.internal;

import com.juanmorschrott.api.reservation.ReservationCreatedEvent;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomTypeInventoryRepository inventoryRepository;
    private final ReservationMapper mapper;
    private final ApplicationEventPublisher events;

    @Transactional
    public ReservationDto create(ReservationDto dto) {
        // Validate date range
        if (!dto.endDate().isAfter(dto.startDate())) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        // 1. Insert reservation as PENDING
        Reservation reservation = mapper.toEntity(dto);
        reservation.setReservationId(null);
        reservation.setStatus(Status.PENDING);
        reservation = reservationRepository.save(reservation);

        // 2. For each date in the range, atomically reserve a room
        dto.startDate().datesUntil(dto.endDate()).forEach(date -> {
            int updated = inventoryRepository.reserveRoom(dto.hotelId(), dto.roomTypeId(), date);
            if (updated == 0) {
                // No availability for this date → exception triggers transaction rollback
                throw new RoomNotAvailableException(dto.hotelId(), dto.roomTypeId(), date);
            }
        });

        // 3. All dates reserved successfully → mark as CONFIRMED
        reservation.setStatus(Status.CONFIRMED);
        reservation = reservationRepository.save(reservation);

        log.info("Reservation {} confirmed for hotel {}, room type {}, dates {}-{}",
                reservation.getReservationId(), dto.hotelId(), dto.roomTypeId(),
                dto.startDate(), dto.endDate());

        // 4. Publish event for cross-module communication
        events.publishEvent(new ReservationCreatedEvent(
                reservation.getReservationId(), reservation.getHotelId(), reservation.getGuestId()));

        return mapper.toDto(reservation);
    }

    @Transactional(readOnly = true)
    public ReservationDto get(Long id) {
        return reservationRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<ReservationDto> getByGuest(Long guestId) {
        return mapper.toDtoList(reservationRepository.findByGuestId(guestId));
    }

    @Transactional
    public void cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        if (reservation.getStatus() == Status.CANCELLED) {
            return; // Idempotent
        }

        // Release inventory for each date in the range
        reservation.getStartDate().datesUntil(reservation.getEndDate())
                .forEach(date -> inventoryRepository.releaseRoom(
                        reservation.getHotelId(), reservation.getRoomTypeId(), date));

        reservation.setStatus(Status.CANCELLED);
        reservationRepository.save(reservation);

        log.info("Reservation {} cancelled", id);
    }

    @Transactional(readOnly = true)
    public List<AvailabilityDto> checkAvailability(Long hotelId, Long roomTypeId,
                                                    LocalDate startDate, LocalDate endDate) {
        List<RoomTypeInventory> inventories = inventoryRepository.findAvailability(
                hotelId, roomTypeId, startDate, endDate);

        return inventories.stream()
                .map(inv -> new AvailabilityDto(
                        inv.getId().hotelId(),
                        inv.getId().roomTypeId(),
                        inv.getId().date(),
                        inv.getTotalInventory(),
                        inv.getTotalReserved(),
                        inv.getAvailableRooms()))
                .toList();
    }
}
