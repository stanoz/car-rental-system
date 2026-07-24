package com.state.street.backend.validation;

import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import com.state.street.backend.services.ValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Clock;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Import(TestTimeConfig.class)
public class ValidationServiceTest {
    @Autowired
    private Clock clock;

    @Autowired
    private ValidationService validationService;

    @Test
    void validateReservationDatesShouldNotThrowWhenStartDateIsBeforeEndDate() {
        LocalDateTime start = LocalDateTime.now(clock).plusHours(1);
        LocalDateTime end = start.plusDays(1);

        assertDoesNotThrow(() ->
                validationService.validateReservationDates(start, end));
    }

    @Test
    void validateReservationDatesShouldThrowWhenStartDateIsAfterEndDate() {
        LocalDateTime end = LocalDateTime.now(clock).plusHours(10);
        LocalDateTime start = end.plusDays(1);

        InvalidDatesException exception = assertThrows(
                InvalidDatesException.class,
                () -> validationService.validateReservationDates(start, end)
        );

        assertEquals("Start date must be before end date.", exception.getMessage());
    }

    @Test
    void validateReservationDatesShouldThrowWhenStartDateEqualsEndDate() {
        LocalDateTime start = LocalDateTime.now(clock).plusHours(1);
        LocalDateTime end = LocalDateTime.now(clock).plusHours(1);

        InvalidDatesException exception = assertThrows(
                InvalidDatesException.class,
                () -> validationService.validateReservationDates(start, end)
        );

        assertEquals("Start date and end date cannot be the same.", exception.getMessage());
    }

    @Test
    void validateReservationDatesShouldThrowWhenStartIsBeforeNow() {
        LocalDateTime start = LocalDateTime.now(clock).minusHours(1);
        LocalDateTime end = start.plusDays(1);

        InvalidDatesException exception = assertThrows(
                InvalidDatesException.class,
                () -> validationService.validateReservationDates(start, end)
        );

        assertEquals("Start date and time must be in the future.", exception.getMessage());
    }
}
