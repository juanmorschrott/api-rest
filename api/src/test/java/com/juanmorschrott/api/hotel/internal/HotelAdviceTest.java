package com.juanmorschrott.api.hotel.internal;

import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelAdviceTest {

    @Test
    void givenHotelNotFoundException_whenHotelNotFound_thenReturnsProblemDetail() {
        HotelAdvice hotelAdvice = new HotelAdvice();
        HotelNotFoundException hotelNotFoundException = new HotelNotFoundException(1L);
        ProblemDetail problemDetail = hotelAdvice.hotelNotFound(hotelNotFoundException);
        assertEquals(404, problemDetail.getStatus());
        assertEquals("Could not find Hotel: 1", problemDetail.getDetail());
    }
}
