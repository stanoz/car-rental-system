package com.state.street.backend.model.dto;

import java.time.LocalDateTime;

public record CreateReservationDto(
        Long carId,
        UserDto user,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
}
