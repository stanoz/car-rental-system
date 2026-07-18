package com.state.street.backend.db;

import com.state.street.backend.model.entity.Car;
import com.state.street.backend.model.entity.CarType;
import com.state.street.backend.model.enums.CarCategory;
import com.state.street.backend.repositories.CarRepository;
import com.state.street.backend.repositories.CarTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DatabaseSeeder {
    private final CarRepository carRepository;
    private final CarTypeRepository carTypeRepository;

    @Transactional
    public void seed() {

        if (carRepository.count() > 0) {
            return;
        }

        for (CarCategory category : CarCategory.values()) {

            CarType type = carTypeRepository.findByType(category)
                    .orElseGet(
                            () -> carTypeRepository.save(CarType.builder()
                                    .type(category)
                                    .build())
                    );

            for (int i = 1; i <= 3; i++) {

                carRepository.save(
                        Car.builder()
                                .brand("Brand" + i)
                                .licensePlate(category.name() + "-" + i)
                                .type(type)
                                .costPerDay(BigDecimal.valueOf(100 + i * 20))
                                .inStock(5)
                                .build()
                );
            }
        }
    }
}
