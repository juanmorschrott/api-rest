package com.juanmorschrott.api.hotel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/hotels")
@RequiredArgsConstructor
class HotelController {

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
