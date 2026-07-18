package com.state.street.backend.mappers;

import com.state.street.backend.model.dto.UserDto;
import com.state.street.backend.model.entity.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .emailAddress(userDto.emailAddress())
                .phoneNumber(userDto.phoneNumber())
                .drivingLicenseId(userDto.drivingLicenseId())
                .build();
    }

    public static UserDto toDto(User userEntity) {
        return UserDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .drivingLicenseId(userEntity.getDrivingLicenseId())
                .emailAddress(userEntity.getEmailAddress())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }
}
