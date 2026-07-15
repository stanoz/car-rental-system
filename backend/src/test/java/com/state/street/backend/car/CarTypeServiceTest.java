package com.state.street.backend.car;

import com.state.street.backend.model.entity.CarType;
import com.state.street.backend.model.enums.CarCategory;
import com.state.street.backend.repositories.CarTypeRepository;
import com.state.street.backend.services.CarTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarTypeServiceTest {
    @Mock
    private CarTypeRepository carTypeRepository;

    @InjectMocks
    private CarTypeService carTypeService;

    @Test
    void getAllCarTypesShouldReturnAllCarTypesWhenFound() {
        CarType mockCarType1 = CarType.builder()
                .id(1L)
                .type(CarCategory.SEDAN)
                .build();
        CarType mockCarType2 = CarType.builder()
                .id(2L)
                .type(CarCategory.SUV)
                .build();

        when(carTypeRepository.findAll()).thenReturn(List.of(mockCarType1, mockCarType2));

        List<CarCategory> results = carTypeService.getAllCarTypes();

        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(2, results.size()),
                () -> assertEquals(CarCategory.SEDAN, results.getFirst()),
                () -> assertEquals(CarCategory.SUV, results.get(1))
        );
    }

    @Test
    void getAllCarTypesShouldReturnEmptyListWhenNoTypesFound() {
        when(carTypeRepository.findAll()).thenReturn(List.of());

        List<CarCategory> results = carTypeService.getAllCarTypes();

        assertAll(
                () -> assertNotNull(results),
                () -> assertEquals(0, results.size())
        );
    }
}
