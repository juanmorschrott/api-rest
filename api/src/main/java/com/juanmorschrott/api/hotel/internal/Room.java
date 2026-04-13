package com.juanmorschrott.api.hotel.internal;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    private Integer floor;

    private Integer number;

    @Column(name = "hotel_id")
    private Long hotelId;

    private String name;

    @Column(name = "is_available")
    private Boolean isAvailable;
}
