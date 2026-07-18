package com.state.street.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserDto(
        @NotBlank(message = "First name cannot be blank.")
        String firstName,

        @NotBlank(message = "Last name cannot be blank.")
        String lastName,

        @NotBlank(message = "Driving license ID cannot be blank.")
        String drivingLicenseId,

        @NotBlank(message = "Email address cannot be blank.")
        @Email(message = "Email address must be valid.")
        @Pattern(
                regexp = "^(?!\\.)(?!.*\\.\\.)([a-z0-9_'+\\-.]*)[a-z0-9_+-]@([a-z0-9][a-z0-9-]*\\.)+[a-z]{2,}$",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Email address must be valid."
        )
        String emailAddress,

        @NotBlank(message = "Phone number cannot be blank.")
        @Pattern(
                regexp = "^[0-9]{9}$",
                message = "Phone number must consist of exactly 9 digits."
        )
        String phoneNumber
) {
}
