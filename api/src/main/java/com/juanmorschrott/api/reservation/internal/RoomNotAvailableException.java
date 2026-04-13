package com.juanmorschrott.api.reservation.internal;

import java.time.LocalDate;

public class RoomNotAvailableException extends RuntimeException {

    public RoomNotAvailableException(Long hotelId, Long roomTypeId, LocalDate date) {
        super(String.format("No rooms available for hotel %d, room type %d on %s", hotelId, roomTypeId, date));
    }
}
