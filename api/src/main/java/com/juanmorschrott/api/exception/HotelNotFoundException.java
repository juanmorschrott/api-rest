package com.juanmorschrott.api.exception;

public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException(Long id) {
        super("Could not find Hotel: " + id);
    }

    public HotelNotFoundException(String name) {
        super("Could not find hotel " + name);
    }

}
