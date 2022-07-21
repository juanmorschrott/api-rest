package com.juanmorschrott.api.exception;

import java.util.function.Supplier;

public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException(Long id) {
        super("Could not find Hotel: " + id);
    }

    public HotelNotFoundException(String name) {
        super("Could not find hotel " + name);
    }

}
