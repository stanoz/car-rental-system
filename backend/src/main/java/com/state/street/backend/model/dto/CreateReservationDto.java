package com.state.street.backend.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateReservationDto(
        @NotNull(message = "carId cannot be null.")
        Long carId,

        @Valid
        @NotNull(message = "User cannot be null.")
        UserDto user,

        @NotNull(message = "Start date and time cannot be null.")
        LocalDateTime startDateTime,

        @NotNull(message = "End date and time cannot be null.")
        LocalDateTime endDateTime
) {
}
