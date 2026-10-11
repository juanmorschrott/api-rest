package com.juanmorschrott.api.hotel.internal;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "hotelId", source = "hotelId")
    Hotel toEntity(HotelDto hotelDto);

    HotelDto toDto(Hotel hotel);

    List<HotelDto> toDtoList(List<Hotel> hotels);

}
