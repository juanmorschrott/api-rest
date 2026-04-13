package com.juanmorschrott.api.reservation.internal;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_type_inventory")
@ToString
public class RoomTypeInventory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoomTypeInventoryId id;

    @Column(name = "total_inventory", nullable = false)
    private Integer totalInventory;

    @Column(name = "total_reserved", nullable = false)
    private Integer totalReserved;

    public int getAvailableRooms() {
        return totalInventory - totalReserved;
    }
}
