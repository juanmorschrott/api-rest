package com.juanmorschrott.api.repository;

import com.juanmorschrott.api.model.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

    Optional<Hotel> findById(Long id);

    Optional<Hotel> findByName(String name);

}
