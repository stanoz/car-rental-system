package com.state.street.backend.model.dto;

import com.state.street.backend.model.enums.CarCategory;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CarDto(
        Long id,
        String brand,
        CarCategory type,
        Integer inStock,
        BigDecimal costPerDay
) {
}
