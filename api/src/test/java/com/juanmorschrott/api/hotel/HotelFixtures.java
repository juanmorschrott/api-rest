package com.juanmorschrott.api.hotel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HotelFixtures {

        public static HotelDto hotelDto = new HotelDto(1L, "Foo", "Test Description", 99.9);

        public static Hotel hotel = new Hotel(1L, "Foo", "Test Description", BigDecimal.valueOf(99.9));

        public static List<Hotel> getHotelsArray() {
                return Arrays.asList(
                                new Hotel(1L, "Foo1", "Test Description 1", BigDecimal.valueOf(10.0)),
                                new Hotel(2L, "Foo2", "Test Description 2", BigDecimal.valueOf(20.0)),
                                new Hotel(3L, "Foo3", "Test Description 3", BigDecimal.valueOf(30.0)));
        }
}
