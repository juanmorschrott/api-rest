package com.juanmorschrott.api.hotel;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HotelDtoToHotelConverter implements Converter<HotelDto, Hotel> {

    @Override
    public Hotel convert(HotelDto source) {

        return Hotel.builder()
                .id(source.id())
                .name(source.name())
                .description(source.description())
                .price(source.price() != null ? BigDecimal.valueOf(source.price()) : null)
                .build();
    }

}
