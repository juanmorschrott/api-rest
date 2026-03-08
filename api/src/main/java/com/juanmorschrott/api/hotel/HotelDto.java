package com.juanmorschrott.api.hotel;

public record HotelDto(
        Long id,
        String name,
        String description,
        Double price) {
}
