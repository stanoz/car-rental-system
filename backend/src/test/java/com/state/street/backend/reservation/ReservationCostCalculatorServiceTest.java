package com.state.street.backend.reservation;

import com.state.street.backend.services.ReservationCostCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReservationCostCalculatorServiceTest {
    @InjectMocks
    private ReservationCostCalculatorService reservationCostCalculatorService;

    @Test
    void calculateShouldReturnOneDayCost() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusDays(1);

        BigDecimal result = reservationCostCalculatorService.calculate(
                start,
                end,
                BigDecimal.valueOf(100)
        );

        assertEquals(BigDecimal.valueOf(100.0).setScale(2), result);
    }

    @Test
    void calculateReservationCostShouldReturnHalfDayCost() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusHours(12);

        BigDecimal result = reservationCostCalculatorService.calculate(
                start,
                end,
                BigDecimal.valueOf(100)
        );

        assertEquals(BigDecimal.valueOf(50.0).setScale(2), result);
    }

    @Test
    void calculateReservationCostShouldReturnTwoAndHalfDaysCost() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusHours(60);

        BigDecimal result = reservationCostCalculatorService.calculate(
                start,
                end,
                BigDecimal.valueOf(100)
        );

        assertEquals(BigDecimal.valueOf(250.0).setScale(2), result);
    }
}
