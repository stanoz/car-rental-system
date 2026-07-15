package com.state.street.backend.model.dto;

import com.state.street.backend.model.enums.CarCategory;
import lombok.Builder;

@Builder
public record ConfirmationCarDto(
        Long id,
        String brand,
        CarCategory type,
        String licensePlate
) {
}
