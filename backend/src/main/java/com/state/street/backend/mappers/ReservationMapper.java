package com.state.street.backend.mappers;

import com.state.street.backend.model.dto.ReservationDto;
import com.state.street.backend.model.entity.Car;
import com.state.street.backend.model.entity.Reservation;
import com.state.street.backend.model.entity.User;

import java.time.LocalDateTime;

public class ReservationMapper {
    private ReservationMapper() {
    }

    public static Reservation toEntity(LocalDateTime startDateTime, LocalDateTime endDateTime, Car car, User user) {
        return Reservation.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .car(car)
                .user(user)
                .build();
    }

    public static ReservationDto toDto(Reservation reservationEntity) {
        return ReservationDto.builder()
                .id(reservationEntity.getId())
                .paymentStatus(reservationEntity.getPaymentStatus())
                .totalCost(reservationEntity.getCost())
                .startDateTime(reservationEntity.getStartDateTime())
                .endDateTime(reservationEntity.getEndDateTime())
                .user(UserMapper.toDto(reservationEntity.getUser()))
                .car(CarMapper.toConfirmationCarDto(reservationEntity.getCar()))
                .build();
    }
}
