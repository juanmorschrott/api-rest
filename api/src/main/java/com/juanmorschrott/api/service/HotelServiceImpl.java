package com.juanmorschrott.api.service;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository repository;

    public HotelServiceImpl() {}

    @Override
    public List<Hotel> list() {
        return (List<Hotel>) repository.findAll();
    }

    @Override
    public Hotel create(Hotel hotel) {
        return repository.save(hotel);
    }

    @Override
    public Hotel get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Hotel getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return repository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        repository.delete(hotel);
    }

}
