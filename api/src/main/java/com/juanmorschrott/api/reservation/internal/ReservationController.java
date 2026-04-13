package com.juanmorschrott.api.reservation.internal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juanmorschrott.api.reservation.AvailabilityDto;
import com.juanmorschrott.api.reservation.ReservationDto;
import com.juanmorschrott.api.reservation.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationDto> create(@Valid @RequestBody ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(reservationDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @GetMapping(params = "guestId")
    public ResponseEntity<List<ReservationDto>> getByGuest(@RequestParam("guestId") Long guestId) {
        return ResponseEntity.ok().body(service.getByGuest(guestId));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> cancel(@PathVariable("id") Long id) {
        service.cancel(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/availability")
    public ResponseEntity<List<AvailabilityDto>> checkAvailability(
            @RequestParam("hotelId") Long hotelId,
            @RequestParam("roomTypeId") Long roomTypeId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok().body(service.checkAvailability(hotelId, roomTypeId, startDate, endDate));
    }
}
