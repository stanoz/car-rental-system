package com.state.street.backend.services;

import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final Clock clock;

    public void validateReservationDates(LocalDateTime start, LocalDateTime end) throws InvalidDatesException {
        if (start.isBefore(LocalDateTime.now(clock))) {
            throw new InvalidDatesException("Start date and time must be in the future.");
        }
        if (start.isAfter(end)) {
            throw new InvalidDatesException("Start date must be before end date.");
        }
        if (start.isEqual(end)) {
            throw new InvalidDatesException("Start date and end date cannot be the same.");
        }
    }
}
