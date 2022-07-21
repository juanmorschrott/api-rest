package com.juanmorschrott.api.fixtures;


import com.juanmorschrott.api.dto.HotelDto;
import com.juanmorschrott.api.model.Hotel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HotelFixtures {

    public static HotelDto hotelDto = HotelDto.builder()
            .id(1)
            .name("Foo")
            .description("Test Description")
            .price(BigDecimal.valueOf(99.9))
            .build();

    public static Hotel hotel = Hotel.builder()
            .id(1)
            .name("Foo")
            .description("Test Description")
            .price(BigDecimal.valueOf(99.9))
            .build();

    public static List<Hotel> getHotelsArray() {
        return Arrays.asList(
                Hotel.builder()
                        .id(1)
                        .name("Foo1")
                        .description("Test Description 1")
                        .price(BigDecimal.valueOf(10.0))
                        .build(),
                Hotel.builder()
                        .id(2)
                        .name("Foo2")
                        .description("Test Description 2")
                        .price(BigDecimal.valueOf(20.0))
                        .build(),
                Hotel.builder()
                        .id(3)
                        .name("Foo3")
                        .description("Test Description 3")
                        .price(BigDecimal.valueOf(30.0))
                        .build());
    }
}
