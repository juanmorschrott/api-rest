package com.juanmorschrott.api.reservation.internal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByGuestId(Long guestId);

    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.hotelId = :hotelId")
    void deleteByHotelId(@Param("hotelId") Long hotelId);
}
