package com.juanmorschrott.api.service;

import com.juanmorschrott.api.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> list();

    Hotel create(Hotel hotel);

    Hotel get(Long id);

    Hotel getByName(String name);

    Hotel update(Hotel hotel);

    void delete(Long id);

}
