package com.juanmorschrott.api.reservation.internal;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long id) {
        super("Could not find Reservation: " + id);
    }
}
