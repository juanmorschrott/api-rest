package com.juanmorschrott.api.hotel.internal;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanmorschrott.api.hotel.HotelDto;
import com.juanmorschrott.api.hotel.HotelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @GetMapping
    public ResponseEntity<List<HotelDto>> list() {
        return ResponseEntity.ok().body(service.list());
    }

    @PostMapping
    public ResponseEntity<HotelDto> create(@Valid @RequestBody HotelDto hotelDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(hotelDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HotelDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable("id") Long id, @Valid @RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok().body(service.update(id, hotelDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
