package com.juanmorschrott.api.reservation.internal;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record RoomTypeInventoryId(
        @Column(name = "hotel_id") Long hotelId,
        @Column(name = "room_type_id") Long roomTypeId,
        @Column(name = "date") LocalDate date) implements Serializable {
}
