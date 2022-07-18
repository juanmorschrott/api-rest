package com.juanmorschrott.api.controller;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/hotels")
public class HotelController {

    private HotelService service;

    @Autowired
    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel) {
        Hotel created = service.create(hotel);
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hotel> get(@PathVariable Long id) {
        Hotel hotel = service.get(id);
        if (hotel != null) {
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Hotel> update(@RequestBody Hotel hotel) {
        Hotel updated = service.update(hotel);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
