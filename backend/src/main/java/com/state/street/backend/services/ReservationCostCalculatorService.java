package com.state.street.backend.services;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@NoArgsConstructor
public class ReservationCostCalculatorService {

    public BigDecimal calculate(LocalDateTime start, LocalDateTime end, BigDecimal carCostPerDay) {
        BigDecimal durationInDays = BigDecimal.valueOf(
                        Duration.between(start, end).toHours())
                .divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);

        return durationInDays.multiply(carCostPerDay);
    }
}
