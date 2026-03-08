package com.juanmorschrott.api.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return mapper.toDto(repository.save(Objects.requireNonNull(hotel)));
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
        if (!repository.existsById(id)) {
            throw new HotelNotFoundException(id);
        }

        Hotel hotel = mapper.toEntity(hotelDto);
        hotel.setId(id);

        return mapper.toDto(repository.save(hotel));
    }

    public void delete(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}
