package com.juanmorschrott.api.reservation.internal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeInventoryRepository extends JpaRepository<RoomTypeInventory, RoomTypeInventoryId> {

    @Query("SELECT i FROM RoomTypeInventory i WHERE i.id.hotelId = :hotelId " +
           "AND i.id.roomTypeId = :roomTypeId " +
           "AND i.id.date >= :startDate AND i.id.date < :endDate")
    List<RoomTypeInventory> findAvailability(
            @Param("hotelId") Long hotelId,
            @Param("roomTypeId") Long roomTypeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Modifying
    @Query("UPDATE RoomTypeInventory i SET i.totalReserved = i.totalReserved + 1 " +
           "WHERE i.id.hotelId = :hotelId AND i.id.roomTypeId = :roomTypeId " +
           "AND i.id.date = :date AND i.totalReserved < i.totalInventory")
    int reserveRoom(
            @Param("hotelId") Long hotelId,
            @Param("roomTypeId") Long roomTypeId,
            @Param("date") LocalDate date);

    @Modifying
    @Query("UPDATE RoomTypeInventory i SET i.totalReserved = i.totalReserved - 1 " +
           "WHERE i.id.hotelId = :hotelId AND i.id.roomTypeId = :roomTypeId " +
           "AND i.id.date = :date AND i.totalReserved > 0")
    int releaseRoom(
            @Param("hotelId") Long hotelId,
            @Param("roomTypeId") Long roomTypeId,
            @Param("date") LocalDate date);
}
