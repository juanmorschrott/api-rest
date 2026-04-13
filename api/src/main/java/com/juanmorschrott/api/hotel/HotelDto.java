package com.juanmorschrott.api.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HotelDto(

                Long hotelId,

                @NotBlank(message = "Name is required")
                String name,

                @NotBlank(message = "Address is required")
                String address,

                @NotNull(message = "Latitude is required")
                Double latitude,

                @NotNull(message = "Longitude is required")
                Double longitude
) {}
