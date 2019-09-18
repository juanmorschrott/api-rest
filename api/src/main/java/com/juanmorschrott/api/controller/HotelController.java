package com.juanmorschrott.api.controller;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/hotels")
public class HotelController {

    private HotelServiceImpl service;

    @Autowired
    public HotelController(HotelServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Hotel> list() {
        return service.list();
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return service.create(hotel);
    }

    @GetMapping(value = "/{id}")
    public Hotel get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping
    public Hotel update(@RequestBody Hotel hotel) {
        return service.update(hotel);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
