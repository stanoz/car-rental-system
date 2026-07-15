package com.state.street.backend.exceptions.car;

public class CarNotFoundException extends Exception {
    public CarNotFoundException(Long id) {
        super(String.format("Car with id: %d not found.", id));
    }
}
