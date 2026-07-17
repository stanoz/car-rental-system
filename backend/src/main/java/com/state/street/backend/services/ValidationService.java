package com.state.street.backend.services;

import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@NoArgsConstructor
public class ValidationService {

    public void validateReservationDates(LocalDateTime start, LocalDateTime end) throws InvalidDatesException {
        if (start.isAfter(end)) {
            throw new InvalidDatesException("Start date must be before end date.");
        }
        if (start.isEqual(end)) {
            throw new InvalidDatesException("Start date and end date cannot be the same.");
        }
    }
}
