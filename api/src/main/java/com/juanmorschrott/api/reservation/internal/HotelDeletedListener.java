package com.juanmorschrott.api.reservation.internal;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import com.juanmorschrott.api.hotel.HotelDeletedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class HotelDeletedListener {

    private final ReservationRepository reservations;
    private final RoomTypeInventoryRepository inventory;

    @ApplicationModuleListener
    void on(HotelDeletedEvent event) {
        reservations.deleteByHotelId(event.id());
        inventory.deleteByHotelId(event.id());
    }
}
