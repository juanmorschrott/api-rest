package com.juanmorschrott.api.reservation.internal;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juanmorschrott.api.hotel.HotelDeletedEvent;

@ExtendWith(MockitoExtension.class)
class HotelDeletedListenerTest {

    @Mock
    private ReservationRepository reservations;

    @Mock
    private RoomTypeInventoryRepository inventory;

    @InjectMocks
    private HotelDeletedListener listener;

    @Test
    void removesReservationsAndInventoryOfDeletedHotel() {
        listener.on(new HotelDeletedEvent(7L));

        verify(reservations).deleteByHotelId(7L);
        verify(inventory).deleteByHotelId(7L);
    }
}
