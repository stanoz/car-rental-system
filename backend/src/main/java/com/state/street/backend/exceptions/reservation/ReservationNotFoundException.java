package com.state.street.backend.exceptions.reservation;

public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(Long id) {
        super(String.format("Reservation with id: %d not found.", id));
    }
}
