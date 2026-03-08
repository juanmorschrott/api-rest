package com.juanmorschrott.api.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    public List<HotelDto> list() {
        return mapper.toDtoList(repository.findAll());
    }

    public HotelDto create(HotelDto hotelDto) {
        Hotel hotel = mapper.toEntity(hotelDto);
        return mapper.toDto(repository.save(hotel));
    }

    public HotelDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    public HotelDto getByName(String name) {
        return repository.findByName(name)
                .map(mapper::toDto)
                .orElseThrow(() -> new HotelNotFoundException(name));
    }

    public HotelDto update(Long id, HotelDto hotelDto) {
        return repository.findById(id)
                .map(existingHotel -> {
                    Hotel hotel = mapper.toEntity(hotelDto);
                    hotel.setId(id);
                    return mapper.toDto(repository.save(hotel));
                })
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    public void delete(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}
