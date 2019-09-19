package com.juanmorschrott.api.controller;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value="hotels", description="Hotels simple REST API")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/hotels")
public class HotelController {

    private HotelServiceImpl service;

    @Autowired
    public HotelController(HotelServiceImpl service) {
        this.service = service;
    }

    @ApiOperation(value = "View a list of available hotels", response = ResponseEntity.class)
    @GetMapping
    public ResponseEntity<List<Hotel>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new Hotel", response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel) {
        Hotel created = service.create(hotel);
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Find an hotel", response = ResponseEntity.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        Hotel hotel = service.get(id);
        if (hotel != null) {
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity("Hotel not found", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Updates an hotel", response = ResponseEntity.class)
    @PutMapping
    public ResponseEntity<Hotel> update(@RequestBody Hotel hotel) {
        Hotel updated = service.update(hotel);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete an hotel", response = ResponseEntity.class)
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
