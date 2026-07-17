package com.state.street.backend.validation;

import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import com.state.street.backend.services.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {
    @InjectMocks
    private ValidationService validationService;

    @Test
    void validateReservationDatesShouldNotThrowWhenStartDateIsBeforeEndDate() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusDays(1);

        assertDoesNotThrow(() ->
                validationService.validateReservationDates(start, end));
    }

    @Test
    void validateReservationDatesShouldThrowWhenStartDateIsAfterEndDate() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 2, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 10, 0);

        InvalidDatesException exception = assertThrows(
                InvalidDatesException.class,
                () -> validationService.validateReservationDates(start, end)
        );

        assertEquals("Start date must be before end date.", exception.getMessage());
    }

    @Test
    void validateReservationDatesShouldThrowWhenStartDateEqualsEndDate() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start;

        InvalidDatesException exception = assertThrows(
                InvalidDatesException.class,
                () -> validationService.validateReservationDates(start, end)
        );

        assertEquals("Start date and end date cannot be the same.", exception.getMessage());
    }
}
