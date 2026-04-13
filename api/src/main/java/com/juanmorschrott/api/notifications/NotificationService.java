package com.juanmorschrott.api.notifications;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import com.juanmorschrott.api.hotel.HotelCreatedEvent;
import com.juanmorschrott.api.reservation.ReservationCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {

    /**
     * Handles the HotelCreatedEvent and sends a simulated notification email.
     * @param event The event to handle.
     */
    @ApplicationModuleListener
    void on(HotelCreatedEvent event) {
        log.info("Received HotelCreatedEvent for hotel '{}' with ID {}. Sending simulated notification email...", 
            event.name(), event.id());
    }

    /**
     * Handles the ReservationCreatedEvent and sends a simulated confirmation email.
     * @param event The event to handle.
     */
    @ApplicationModuleListener
    void on(ReservationCreatedEvent event) {
        log.info("Received ReservationCreatedEvent for reservation {} at hotel {} for guest {}. Sending simulated confirmation email...",
            event.reservationId(), event.hotelId(), event.guestId());
    }
}

