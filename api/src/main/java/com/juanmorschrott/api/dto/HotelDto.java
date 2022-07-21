package com.juanmorschrott.api.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class HotelDto {

    private long id;

    private String name;

    private String description;

    private double price;

}
