package com.state.street.backend.model.dto;

import com.state.street.backend.model.enums.CarCategory;

public record ConfirmationCarDto(
        Long id,
        String brand,
        CarCategory type,
        String licensePlate
) {
}
