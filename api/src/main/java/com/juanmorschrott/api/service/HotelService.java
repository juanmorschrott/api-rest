package com.juanmorschrott.api.service;

import java.util.List;
import java.util.Optional;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    public HotelService() {}

    public List<Hotel> list() {
        return (List<Hotel>) repository.findAll();
    }

    public Hotel create(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Hotel getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Hotel update(Hotel hotel) {
        return repository.save(hotel);
    }

    public void delete(Long id) {
        Optional<Hotel> hotel = repository.findById(id);
        hotel.ifPresent(value -> repository.delete(value));
    }

}
