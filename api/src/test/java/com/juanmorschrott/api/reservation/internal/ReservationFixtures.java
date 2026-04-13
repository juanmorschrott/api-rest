package com.juanmorschrott.api.reservation.internal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.juanmorschrott.api.reservation.ReservationDto;

import com.juanmorschrott.api.reservation.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationFixtures {

    public static final LocalDate START_DATE = LocalDate.of(2026, 5, 1);
    public static final LocalDate END_DATE = LocalDate.of(2026, 5, 4);

    public static ReservationDto reservationDto = new ReservationDto(
            1L, 1L, 1L, START_DATE, END_DATE, Status.CONFIRMED, 1L);

    public static ReservationDto createReservationDto = new ReservationDto(
            null, 1L, 1L, START_DATE, END_DATE, null, 1L);

    public static Reservation reservation = Reservation.builder()
            .reservationId(1L)
            .hotelId(1L)
            .roomTypeId(1L)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .status(Status.CONFIRMED)
            .guestId(1L)
            .build();

    public static Reservation pendingReservation = Reservation.builder()
            .reservationId(2L)
            .hotelId(1L)
            .roomTypeId(2L)
            .startDate(LocalDate.of(2026, 5, 5))
            .endDate(LocalDate.of(2026, 5, 7))
            .status(Status.PENDING)
            .guestId(2L)
            .build();

    public static List<Reservation> getReservationsList() {
        return Arrays.asList(
                reservation,
                pendingReservation,
                Reservation.builder()
                        .reservationId(3L)
                        .hotelId(2L)
                        .roomTypeId(1L)
                        .startDate(LocalDate.of(2026, 5, 10))
                        .endDate(LocalDate.of(2026, 5, 12))
                        .status(Status.CANCELLED)
                        .guestId(3L)
                        .build());
    }

    public static List<ReservationDto> getReservationsDtoList() {
        return Arrays.asList(
                reservationDto,
                new ReservationDto(2L, 1L, 2L,
                        LocalDate.of(2026, 5, 5), LocalDate.of(2026, 5, 7),
                        Status.PENDING, 2L),
                new ReservationDto(3L, 2L, 1L,
                        LocalDate.of(2026, 5, 10), LocalDate.of(2026, 5, 12),
                        Status.CANCELLED, 3L));
    }
}
