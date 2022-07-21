package com.juanmorschrott.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class HotelDto {

    private long id;

    private String name;

    private String description;

    private BigDecimal price;

}
