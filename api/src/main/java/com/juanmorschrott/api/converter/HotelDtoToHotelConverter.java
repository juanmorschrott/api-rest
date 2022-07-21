package com.juanmorschrott.api.converter;


import com.juanmorschrott.api.dto.HotelDto;
import com.juanmorschrott.api.model.Hotel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HotelDtoToHotelConverter implements Converter<HotelDto, Hotel> {

    @Override
    public Hotel convert(HotelDto source) {

        return Hotel.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .build();
    }

}
