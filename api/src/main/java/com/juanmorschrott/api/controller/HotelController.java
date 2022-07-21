package com.juanmorschrott.api.controller;

import com.juanmorschrott.api.dto.HotelDto;
import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/hotels")
public class HotelController {

    private HotelService service;

    public HotelController(HotelService service) {

        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> list() {

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody HotelDto hotelDto) {

        return new ResponseEntity<>(service.create(hotelDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hotel> get(@PathVariable Long id) {

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Hotel> update(@RequestBody HotelDto hotelDto) {

        return new ResponseEntity<>(service.update(hotelDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
