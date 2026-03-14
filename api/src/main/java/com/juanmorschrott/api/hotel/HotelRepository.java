package com.juanmorschrott.api.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findById(Long id);

    Optional<Hotel> findByName(String name);

}
