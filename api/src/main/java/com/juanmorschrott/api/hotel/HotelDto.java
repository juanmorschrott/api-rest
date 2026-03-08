package com.juanmorschrott.api.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HotelDto(

                Long id,

                @NotBlank(message = "Name is required")
                String name,

                @NotBlank(message = "Description is required")
                String description,

                @NotNull(message = "Price is required")
                @Positive
                Double price
) {}
