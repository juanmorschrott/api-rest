package com.juanmorschrott.api.reservation;

import java.time.LocalDate;

public record AvailabilityDto(
        Long hotelId,
        Long roomTypeId,
        LocalDate date,
        int totalInventory,
        int totalReserved,
        int availableRooms
) {
}
