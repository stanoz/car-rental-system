package com.state.street.backend.services;

import com.state.street.backend.exceptions.car.CarNotAvailableException;
import com.state.street.backend.exceptions.car.CarNotFoundException;
import com.state.street.backend.exceptions.car.InvalidCarStockAmountException;
import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import com.state.street.backend.exceptions.user.UserNotFoundException;
import com.state.street.backend.mappers.ReservationMapper;
import com.state.street.backend.mappers.UserMapper;
import com.state.street.backend.model.dto.CreateReservationDto;
import com.state.street.backend.model.entity.Car;
import com.state.street.backend.model.entity.Reservation;
import com.state.street.backend.model.entity.User;
import com.state.street.backend.model.enums.PaymentStatus;
import com.state.street.backend.model.enums.ReservationStatus;
import com.state.street.backend.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarService carService;
    private final UserService userService;
    private final ReservationCostCalculatorService reservationCostCalculatorService;

    public void createReservation(CreateReservationDto createReservationDto) throws CarNotFoundException, CarNotAvailableException, InvalidDatesException, InvalidCarStockAmountException, UserNotFoundException {
        this.validateReservationDates(createReservationDto.startDateTime(), createReservationDto.endDateTime());
        Car selectedCar = this.carService.getCarById(createReservationDto.carId());
        if (selectedCar.getInStock() == 0) {
            throw new CarNotAvailableException();
        }
        selectedCar = this.carService.decrementCarInStock(createReservationDto.carId(), 1);
        User userEntity;
        Optional<Long> existingUserId = this.userService.checkIfUserAlreadyExists(UserMapper.toEntity(createReservationDto.user()));
        if (existingUserId.isEmpty()) {
            userEntity = this.userService.createNewUser(createReservationDto.user());
        } else {
            userEntity = this.userService.getUserById(existingUserId.get());
        }
        Reservation reservationEntity = ReservationMapper
                .toEntity(createReservationDto.startDateTime(), createReservationDto.endDateTime(), selectedCar, userEntity);
        BigDecimal totalReservationCost = this.reservationCostCalculatorService.calculate(createReservationDto.startDateTime(), createReservationDto.endDateTime(), selectedCar.getCostPerDay());
        reservationEntity.setCost(totalReservationCost);
        reservationEntity.setPaymentStatus(PaymentStatus.PAID);
        reservationEntity.setReservationStatus(ReservationStatus.OPEN);
        this.reservationRepository.save(reservationEntity);
    }

    private void validateReservationDates(LocalDateTime start, LocalDateTime end) throws InvalidDatesException {
        if (start.isAfter(end)) {
            throw new InvalidDatesException("Start date must be before end date.");
        }
        if (start.isEqual(end)) {
            throw new InvalidDatesException("Start date and end date cannot be the same.");
        }
    }

}
