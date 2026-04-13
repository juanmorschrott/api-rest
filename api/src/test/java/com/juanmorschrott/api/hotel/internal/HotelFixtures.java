package com.juanmorschrott.api.hotel.internal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.juanmorschrott.api.hotel.HotelDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HotelFixtures {

        public static HotelDto hotelDto = new HotelDto(1L, "Atenea", "Calle Mayor 10, Madrid", 40.4168000, -3.7037900);

        public static Hotel hotel = new Hotel(
                        1L,
                        "Atenea",
                        "Calle Mayor 10, Madrid",
                        BigDecimal.valueOf(40.4168000),
                        BigDecimal.valueOf(-3.7037900));

        public static List<Hotel> getHotelsArray() {
                return Arrays.asList(
                                new Hotel(
                                                1L,
                                                "Atenea",
                                                "Calle Mayor 10, Madrid",
                                                BigDecimal.valueOf(40.4168000),
                                                BigDecimal.valueOf(-3.7037900)),
                                new Hotel(
                                                2L,
                                                "Kontiki",
                                                "Avenida del Mar 5, Barcelona",
                                                BigDecimal.valueOf(41.3851000),
                                                BigDecimal.valueOf(2.1734000)),
                                new Hotel(
                                                3L,
                                                "Apollo",
                                                "Plaza España 2, Sevilla",
                                                BigDecimal.valueOf(37.3891000),
                                                BigDecimal.valueOf(-5.9844600)));
        }

        public static List<HotelDto> getHotelsDtoList() {
                return Arrays.asList(
                                new HotelDto(1L, "Atenea", "Calle Mayor 10, Madrid", 40.4168000, -3.7037900),
                                new HotelDto(2L, "Kontiki", "Avenida del Mar 5, Barcelona", 41.3851000, 2.1734000),
                                new HotelDto(3L, "Apollo", "Plaza España 2, Sevilla", 37.3891000, -5.9844600));
        }
}
