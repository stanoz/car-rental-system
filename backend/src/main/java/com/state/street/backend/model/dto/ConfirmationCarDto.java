package com.state.street.backend.model.dto;

import com.state.street.backend.model.enums.CarCategory;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ConfirmationCarDto(
        String brand,
        CarCategory type,
        String licensePlate,
        BigDecimal costPerDay
) {
}
