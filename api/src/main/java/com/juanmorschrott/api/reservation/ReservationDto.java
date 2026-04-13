package com.juanmorschrott.api.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationDto(

        Long reservationId,

        @NotNull(message = "Hotel ID is required")
        Long hotelId,

        @NotNull(message = "Room type ID is required")
        Long roomTypeId,

        @NotNull(message = "Start date is required")
        @Future(message = "Start date must be in the future")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        @Future(message = "End date must be in the future")
        LocalDate endDate,

        Status status,

        @NotNull(message = "Guest ID is required")
        Long guestId
) {
}
