package com.juanmorschrott.api.reservation.internal;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "reservationId", source = "reservationId")
    Reservation toEntity(ReservationDto reservationDto);

    ReservationDto toDto(Reservation reservation);

    List<ReservationDto> toDtoList(List<Reservation> reservations);
}
