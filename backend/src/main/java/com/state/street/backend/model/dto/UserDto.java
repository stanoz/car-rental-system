package com.state.street.backend.model.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String firstName,
        String lastName,
        String drivingLicenseId,
        String emailAddress,
        String phoneNumber
) {
}
