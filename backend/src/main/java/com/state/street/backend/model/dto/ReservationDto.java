package com.state.street.backend.model.dto;

import com.state.street.backend.model.enums.PaymentStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ReservationDto(
        Long id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        BigDecimal totalCost,
        PaymentStatus paymentStatus,
        UserDto user,
        ConfirmationCarDto car
) {
}
