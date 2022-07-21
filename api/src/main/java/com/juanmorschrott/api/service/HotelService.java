package com.juanmorschrott.api.service;

import com.juanmorschrott.api.converter.HotelDtoToHotelConverter;
import com.juanmorschrott.api.dto.HotelDto;
import com.juanmorschrott.api.exception.HotelNotFoundException;
import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository repository;
    private HotelDtoToHotelConverter converter;

    public HotelService(HotelRepository repository, HotelDtoToHotelConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<Hotel> list() {

        return repository.findAll();
    }

    public Hotel create(HotelDto hotelDto) {
        Hotel hotel = this.converter.convert(hotelDto);

        return repository.save(Objects.requireNonNull(hotel));
    }

    public Hotel get(Long id) {

        return repository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

    public Hotel getByName(String name) {

        return repository.findByName(name).orElseThrow(() -> new HotelNotFoundException(name));
    }

    public Hotel update(HotelDto hotelDto) {
        Hotel hotel = this.converter.convert(hotelDto);

        return repository.save(Objects.requireNonNull(hotel));
    }

    public void delete(Long id) {
        Optional<Hotel> hotel = repository.findById(id);

        hotel.ifPresent(value -> repository.delete(value));
    }

}
