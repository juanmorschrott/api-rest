package com.juanmorschrott.api.reservation.internal;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RoomTypeInventoryId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "room_type_id")
    private Long roomTypeId;

    @Column(name = "date")
    private LocalDate date;
}
