package com.state.street.backend.services;

import com.state.street.backend.exceptions.car.CarNotFoundException;
import com.state.street.backend.mappers.CarMapper;
import com.state.street.backend.model.dto.CarDto;
import com.state.street.backend.model.entity.Car;
import com.state.street.backend.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarDto> getAllCars() {
        return this.carRepository.findAll()
                .stream()
                .map(CarMapper::toCarDto)
                .toList();
    }

    public Car getCarById(Long carId) throws CarNotFoundException {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
    }
}
