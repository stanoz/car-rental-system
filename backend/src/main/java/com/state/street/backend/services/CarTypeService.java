package com.state.street.backend.services;

import com.state.street.backend.model.entity.CarType;
import com.state.street.backend.model.enums.CarCategory;
import com.state.street.backend.repositories.CarTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarTypeService {
    private final CarTypeRepository carTypeRepository;

    public List<CarCategory> getAllCarTypes() {
        return carTypeRepository.findAll().stream().map((CarType::getType)).toList();
    }
}
