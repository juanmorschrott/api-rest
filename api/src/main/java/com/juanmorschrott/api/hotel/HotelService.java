package com.juanmorschrott.api.hotel;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juanmorschrott.api.hotel.internal.Hotel;
import com.juanmorschrott.api.hotel.internal.HotelMapper;
import com.juanmorschrott.api.hotel.internal.HotelNotFoundException;
import com.juanmorschrott.api.hotel.internal.HotelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository repository;
    private final HotelMapper mapper;
    private final ApplicationEventPublisher events;

    @Transactional(readOnly = true)
    public List<HotelDto> list() {
        return mapper.toDtoList(repository.findAll());
    }

    @Transactional
    public HotelDto create(HotelDto hotelDto) {
        Hotel hotel = mapper.toEntity(hotelDto);
        Hotel savedHotel = repository.save(hotel);
        events.publishEvent(new HotelCreatedEvent(savedHotel.getId(), savedHotel.getName()));
        return mapper.toDto(savedHotel);
    }

    @Transactional(readOnly = true)
    public HotelDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public HotelDto getByName(String name) {
        return repository.findByName(name)
                .map(mapper::toDto)
                .orElseThrow(() -> new HotelNotFoundException(name));
    }

    @Transactional
    public HotelDto update(Long id, HotelDto hotelDto) {
        return repository.findById(id)
                .map(existingHotel -> {
                    Hotel hotel = mapper.toEntity(hotelDto);
                    hotel.setId(id);
                    return mapper.toDto(repository.save(hotel));
                })
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}
