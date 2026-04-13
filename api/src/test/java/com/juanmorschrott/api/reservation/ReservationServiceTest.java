package com.juanmorschrott.api.reservation;

import com.juanmorschrott.api.reservation.internal.Reservation;
import com.juanmorschrott.api.reservation.internal.ReservationFixtures;
import com.juanmorschrott.api.reservation.internal.ReservationMapper;
import com.juanmorschrott.api.reservation.internal.ReservationNotFoundException;
import com.juanmorschrott.api.reservation.internal.ReservationRepository;
import com.juanmorschrott.api.reservation.internal.RoomNotAvailableException;
import com.juanmorschrott.api.reservation.internal.RoomTypeInventory;
import com.juanmorschrott.api.reservation.internal.RoomTypeInventoryId;
import com.juanmorschrott.api.reservation.internal.RoomTypeInventoryRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomTypeInventoryRepository inventoryRepository;

    @Mock
    private ReservationMapper mapper;

    @Mock
    private ApplicationEventPublisher events;

    @Test
    public void whenCreate_thenReservationIsConfirmed() {
        // given
        ReservationDto createDto = ReservationFixtures.createReservationDto;
        Reservation pendingReservation = Reservation.builder()
                .reservationId(1L)
                .hotelId(1L).roomTypeId(1L)
                .startDate(ReservationFixtures.START_DATE)
                .endDate(ReservationFixtures.END_DATE)
                .status(Status.PENDING)
                .guestId(1L)
                .build();
        Reservation confirmedReservation = Reservation.builder()
                .reservationId(1L)
                .hotelId(1L).roomTypeId(1L)
                .startDate(ReservationFixtures.START_DATE)
                .endDate(ReservationFixtures.END_DATE)
                .status(Status.CONFIRMED)
                .guestId(1L)
                .build();

        when(mapper.toEntity(createDto)).thenReturn(pendingReservation);
        when(reservationRepository.save(any(Reservation.class)))
                .thenReturn(pendingReservation)
                .thenReturn(confirmedReservation);
        // All 3 dates (May 1, 2, 3) have availability
        when(inventoryRepository.reserveRoom(eq(1L), eq(1L), any(LocalDate.class))).thenReturn(1);
        when(mapper.toDto(confirmedReservation)).thenReturn(ReservationFixtures.reservationDto);

        // when
        ReservationDto result = reservationService.create(createDto);

        // then
        assertThat(result.status()).isEqualTo(Status.CONFIRMED);
        verify(inventoryRepository, times(3)).reserveRoom(eq(1L), eq(1L), any(LocalDate.class));
        verify(reservationRepository, times(2)).save(any(Reservation.class));
    }

    @Test
    public void whenCreate_andNoAvailability_thenThrowsException() {
        // given
        ReservationDto createDto = ReservationFixtures.createReservationDto;
        Reservation pendingReservation = Reservation.builder()
                .reservationId(1L)
                .hotelId(1L).roomTypeId(1L)
                .startDate(ReservationFixtures.START_DATE)
                .endDate(ReservationFixtures.END_DATE)
                .status(Status.PENDING)
                .guestId(1L)
                .build();

        when(mapper.toEntity(createDto)).thenReturn(pendingReservation);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(pendingReservation);
        // First date OK, second date no availability
        when(inventoryRepository.reserveRoom(eq(1L), eq(1L), eq(ReservationFixtures.START_DATE))).thenReturn(1);
        when(inventoryRepository.reserveRoom(eq(1L), eq(1L), eq(ReservationFixtures.START_DATE.plusDays(1)))).thenReturn(0);

        // when/then
        assertThatThrownBy(() -> reservationService.create(createDto))
                .isInstanceOf(RoomNotAvailableException.class);
    }

    @Test
    public void whenGet_thenReturnReservation() {
        // given
        Reservation reservation = ReservationFixtures.reservation;
        ReservationDto dto = ReservationFixtures.reservationDto;

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(mapper.toDto(reservation)).thenReturn(dto);

        // when
        ReservationDto result = reservationService.get(1L);

        // then
        assertThat(result.reservationId()).isEqualTo(1L);
        assertThat(result.status()).isEqualTo(Status.CONFIRMED);
    }

    @Test
    public void whenGetNotFound_thenThrowsException() {
        // given
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when/then
        assertThatThrownBy(() -> reservationService.get(999L))
                .isInstanceOf(ReservationNotFoundException.class);
    }

    @Test
    public void whenGetByGuest_thenReturnGuestReservations() {
        // given
        List<Reservation> reservations = List.of(ReservationFixtures.reservation);
        List<ReservationDto> dtos = List.of(ReservationFixtures.reservationDto);

        when(reservationRepository.findByGuestId(1L)).thenReturn(reservations);
        when(mapper.toDtoList(reservations)).thenReturn(dtos);

        // when
        List<ReservationDto> result = reservationService.getByGuest(1L);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).guestId()).isEqualTo(1L);
    }

    @Test
    public void whenCancel_thenStatusIsCancelledAndInventoryReleased() {
        // given
        Reservation reservation = Reservation.builder()
                .reservationId(1L)
                .hotelId(1L).roomTypeId(1L)
                .startDate(ReservationFixtures.START_DATE)
                .endDate(ReservationFixtures.END_DATE)
                .status(Status.CONFIRMED)
                .guestId(1L)
                .build();

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(inventoryRepository.releaseRoom(eq(1L), eq(1L), any(LocalDate.class))).thenReturn(1);

        // when
        reservationService.cancel(1L);

        // then
        assertThat(reservation.getStatus()).isEqualTo(Status.CANCELLED);
        verify(inventoryRepository, times(3)).releaseRoom(eq(1L), eq(1L), any(LocalDate.class));
        verify(reservationRepository).save(reservation);
    }

    @Test
    public void whenCancelAlreadyCancelled_thenIdempotent() {
        // given
        Reservation reservation = Reservation.builder()
                .reservationId(1L)
                .hotelId(1L).roomTypeId(1L)
                .startDate(ReservationFixtures.START_DATE)
                .endDate(ReservationFixtures.END_DATE)
                .status(Status.CANCELLED)
                .guestId(1L)
                .build();

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // when
        reservationService.cancel(1L);

        // then — no inventory operations, no save
        verify(inventoryRepository, never()).releaseRoom(anyLong(), anyLong(), any(LocalDate.class));
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    public void whenCheckAvailability_thenReturnInventoryData() {
        // given
        LocalDate date = LocalDate.of(2026, 5, 1);
        RoomTypeInventory inventory = RoomTypeInventory.builder()
                .id(new RoomTypeInventoryId(1L, 1L, date))
                .totalInventory(10)
                .totalReserved(2)
                .build();

        when(inventoryRepository.findAvailability(1L, 1L, date, date.plusDays(1)))
                .thenReturn(List.of(inventory));

        // when
        List<AvailabilityDto> result = reservationService.checkAvailability(1L, 1L, date, date.plusDays(1));

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).availableRooms()).isEqualTo(8);
        assertThat(result.get(0).totalInventory()).isEqualTo(10);
        assertThat(result.get(0).totalReserved()).isEqualTo(2);
    }
}
