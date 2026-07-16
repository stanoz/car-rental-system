package com.state.street.backend.exceptions.car;

public class CarNotAvailableException extends Exception {
    public CarNotAvailableException() {
        super("Selected car is not available.");
    }
}
