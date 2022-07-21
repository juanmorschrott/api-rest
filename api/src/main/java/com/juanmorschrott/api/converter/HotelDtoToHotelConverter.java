package com.juanmorschrott.api.converter;


import com.juanmorschrott.api.dto.HotelDto;
import com.juanmorschrott.api.model.Hotel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HotelDtoToHotelConverter implements Converter<HotelDto, Hotel> {

    @Override
    public Hotel convert(HotelDto source) {

        return Hotel.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(BigDecimal.valueOf(source.getPrice()))
                .build();
    }

}
