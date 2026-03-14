package com.juanmorschrott.api.hotel.internal;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.juanmorschrott.api.hotel.HotelDto;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "id", source = "id")
    Hotel toEntity(HotelDto hotelDto);

    HotelDto toDto(Hotel hotel);

    List<HotelDto> toDtoList(List<Hotel> hotels);

}
