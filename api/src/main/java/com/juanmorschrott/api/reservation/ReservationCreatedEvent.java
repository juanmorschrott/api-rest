package com.juanmorschrott.api.reservation;

public record ReservationCreatedEvent(Long reservationId, Long hotelId, Long guestId) {
}
