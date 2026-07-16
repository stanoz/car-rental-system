package com.state.street.backend.model.dto;

public record UserDto(
        String firstName,
        String lastName,
        String drivingLicenseId,
        String emailAddress,
        String phoneNumber
) {
}
