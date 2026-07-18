package com.state.street.backend.controllers;

import com.state.street.backend.exceptions.car.CarNotAvailableException;
import com.state.street.backend.exceptions.car.CarNotFoundException;
import com.state.street.backend.exceptions.car.InvalidCarStockAmountException;
import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import com.state.street.backend.exceptions.reservation.ReservationNotFoundException;
import com.state.street.backend.exceptions.user.UserNotFoundException;
import com.state.street.backend.model.dto.CreateReservationDto;
import com.state.street.backend.model.dto.ReservationDto;
import com.state.street.backend.services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> createReservation(@Valid @RequestBody CreateReservationDto createReservationDto) throws UserNotFoundException, CarNotAvailableException, InvalidDatesException, InvalidCarStockAmountException, CarNotFoundException {
        this.reservationService.createReservation(createReservationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) throws ReservationNotFoundException {
        return ResponseEntity.ok(this.reservationService.getReservationById(id));
    }
}
