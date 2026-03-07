package com.juanmorschrott.api.hotel;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class HotelDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

}
