package com.state.street.backend.controllers;

import com.state.street.backend.model.dto.CarDto;
import com.state.street.backend.model.enums.CarCategory;
import com.state.street.backend.services.CarService;
import com.state.street.backend.services.CarTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/cars"))
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CarController {
    private final CarService carService;
    private final CarTypeService carTypeService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/types")
    public ResponseEntity<List<CarCategory>> getAllCarTypes() {
        return ResponseEntity.ok(carTypeService.getAllCarTypes());
    }
}
