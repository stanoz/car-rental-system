package com.state.street.backend.car;

import com.state.street.backend.exceptions.car.CarNotFoundException;
import com.state.street.backend.model.dto.CarDto;
import com.state.street.backend.model.entity.Car;
import com.state.street.backend.model.entity.CarType;
import com.state.street.backend.model.enums.CarCategory;
import com.state.street.backend.repositories.CarRepository;
import com.state.street.backend.services.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void getAllCarsShouldReturnAllCarsWhenFound() {
        CarType mockCarType1 = CarType.builder()
                .id(1L)
                .type(CarCategory.SEDAN)
                .build();
        CarType mockCarType2 = CarType.builder()
                .id(2L)
                .type(CarCategory.SUV)
                .build();
        Car mockCar1 = Car.builder()
                .id(1L)
                .brand("brand1")
                .inStock(1)
                .licensePlate("1234")
                .type(mockCarType1)
                .build();
        Car mockCar2 = Car.builder()
                .id(2L)
                .brand("brand2")
                .inStock(12)
                .licensePlate("5678")
                .type(mockCarType2)
                .build();

        when(carRepository.findAll()).thenReturn(List.of(mockCar1, mockCar2));

        List<CarDto> results = carService.getAllCars();
        CarDto carResult1 = results.getFirst();
        CarDto carResult2 = results.get(1);
        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(2, results.size()),
                () -> assertEquals(mockCar1.getId(), carResult1.id()),
                () -> assertEquals(mockCar1.getBrand(), carResult1.brand()),
                () -> assertEquals(mockCar1.getInStock(), carResult1.inStock()),
                () -> assertEquals(CarCategory.SEDAN, carResult1.type()),
                () -> assertEquals(mockCar2.getId(), carResult2.id()),
                () -> assertEquals(mockCar2.getBrand(), carResult2.brand()),
                () -> assertEquals(mockCar2.getInStock(), carResult2.inStock()),
                () -> assertEquals(CarCategory.SUV, carResult2.type())
        );
    }

    @Test
    void getAllCarsShouldReturnEmptyListWhenNoCarsFound() {
        when(carRepository.findAll()).thenReturn(List.of());

        List<CarDto> results = carService.getAllCars();

        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(0, results.size())
        );
    }

    @Test
    void getCarByIdShouldReturnCarWhenFound() throws CarNotFoundException {
        CarType mockCarType = CarType.builder()
                .id(1L)
                .type(CarCategory.VAN)
                .build();
        Car mockCar = Car.builder()
                .id(1L)
                .brand("brand")
                .inStock(1)
                .licensePlate("1234")
                .type(mockCarType)
                .build();

        when(carRepository.findById(1L)).thenReturn(Optional.of(mockCar));

        Car result = carService.getCarById(1L);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(mockCar.getId(), result.getId()),
                () -> assertEquals(mockCar.getBrand(), result.getBrand()),
                () -> assertEquals(mockCar.getLicensePlate(), result.getLicensePlate()),
                () -> assertEquals(CarCategory.VAN, result.getType().getType()),
                () -> assertEquals(mockCar.getInStock(), result.getInStock())
        );
    }

    @Test
    void getCarByIdShouldThrowCarNotFoundExceptionWhenCarNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.getCarById(1L));
    }
}
