package com.state.street.backend.mappers;

import com.state.street.backend.model.dto.CarDto;
import com.state.street.backend.model.dto.ConfirmationCarDto;
import com.state.street.backend.model.entity.Car;

public class CarMapper {
    private CarMapper(){}

    public static CarDto toCarDto(Car carEntity) {
        return CarDto.builder()
                .id(carEntity.getId())
                .brand(carEntity.getBrand())
                .type(carEntity.getType().getType())
                .inStock(carEntity.getInStock())
                .costPerDay(carEntity.getCostPerDay())
                .build();
    }

    public static ConfirmationCarDto toConfirmationCarDto(Car carEntity) {
        return ConfirmationCarDto.builder()
                .id(carEntity.getId())
                .brand(carEntity.getBrand())
                .type(carEntity.getType().getType())
                .licensePlate(carEntity.getLicensePlate())
                .build();
    }
}
