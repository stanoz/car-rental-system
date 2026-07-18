package com.state.street.backend.reservation;

import com.state.street.backend.exceptions.car.CarNotAvailableException;
import com.state.street.backend.exceptions.car.CarNotFoundException;
import com.state.street.backend.exceptions.car.InvalidCarStockAmountException;
import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import com.state.street.backend.exceptions.user.UserNotFoundException;
import com.state.street.backend.model.dto.CreateReservationDto;
import com.state.street.backend.model.dto.UserDto;
import com.state.street.backend.model.entity.Car;
import com.state.street.backend.model.entity.Reservation;
import com.state.street.backend.model.entity.User;
import com.state.street.backend.model.enums.PaymentStatus;
import com.state.street.backend.model.enums.ReservationStatus;
import com.state.street.backend.repositories.ReservationRepository;
import com.state.street.backend.services.CarService;
import com.state.street.backend.services.ReservationCostCalculatorService;
import com.state.street.backend.services.ReservationService;
import com.state.street.backend.services.UserService;
import com.state.street.backend.services.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CarService carService;

    @Mock
    private UserService userService;

    @Mock
    private ReservationCostCalculatorService reservationCostCalculatorService;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private ReservationService reservationService;

    @Captor
    private ArgumentCaptor<Reservation> reservationCaptor;

    @Test
    void createReservationShouldCreateReservationForNewUser() throws CarNotFoundException, InvalidCarStockAmountException, UserNotFoundException, CarNotAvailableException, InvalidDatesException {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusDays(2);

        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                userDto,
                start,
                end
        );

        Car car = Car.builder()
                .id(1L)
                .brand("Brand")
                .licensePlate("GD12345")
                .costPerDay(BigDecimal.valueOf(100))
                .inStock(1)
                .build();

        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        Reservation savedReservation = Reservation.builder()
                .id(1L)
                .car(car)
                .user(user)
                .startDateTime(start)
                .endDateTime(end)
                .cost(BigDecimal.valueOf(200))
                .paymentStatus(PaymentStatus.PAID)
                .reservationStatus(ReservationStatus.OPEN)
                .build();

        when(carService.getCarById(1L)).thenReturn(car);
        when(carService.decrementCarInStock(1L, 1)).thenReturn(car);

        when(userService.checkIfUserAlreadyExists(any(User.class))).thenReturn(Optional.empty());

        when(userService.createNewUser(userDto)).thenReturn(user);

        when(reservationCostCalculatorService.calculate(start, end, car.getCostPerDay())).thenReturn(BigDecimal.valueOf(200));

        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        Long reservationId = reservationService.createReservation(dto);

        verify(validationService).validateReservationDates(start, end);
        verify(reservationRepository).save(reservationCaptor.capture());

        Reservation saved = reservationCaptor.getValue();

        assertAll(
                () -> assertEquals(1L, reservationId),
                () -> assertEquals(car, saved.getCar()),
                () -> assertEquals(user, saved.getUser()),
                () -> assertEquals(start, saved.getStartDateTime()),
                () -> assertEquals(end, saved.getEndDateTime()),
                () -> assertEquals(BigDecimal.valueOf(200), saved.getCost()),
                () -> assertEquals(PaymentStatus.PAID, saved.getPaymentStatus()),
                () -> assertEquals(ReservationStatus.OPEN, saved.getReservationStatus())
        );

        verify(userService).createNewUser(userDto);
        verify(userService, never()).getUserById(anyLong());
    }

    @Test
    void createReservationShouldUseExistingUserWhenUserAlreadyExists() throws CarNotFoundException, InvalidCarStockAmountException, UserNotFoundException, CarNotAvailableException, InvalidDatesException {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusDays(2);

        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                userDto,
                start,
                end
        );

        Car car = Car.builder()
                .id(1L)
                .brand("Brand")
                .licensePlate("GD12345")
                .costPerDay(BigDecimal.valueOf(100))
                .inStock(1)
                .build();

        User existingUser = User.builder()
                .id(5L)
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        Reservation savedReservation = Reservation.builder()
                .id(1L)
                .car(car)
                .user(existingUser)
                .startDateTime(start)
                .endDateTime(end)
                .cost(BigDecimal.valueOf(200))
                .paymentStatus(PaymentStatus.PAID)
                .reservationStatus(ReservationStatus.OPEN)
                .build();

        when(carService.getCarById(1L)).thenReturn(car);
        when(carService.decrementCarInStock(1L, 1)).thenReturn(car);

        when(userService.checkIfUserAlreadyExists(any(User.class))).thenReturn(Optional.of(5L));

        when(userService.getUserById(5L)).thenReturn(existingUser);

        when(reservationCostCalculatorService.calculate(start, end, car.getCostPerDay()))
                .thenReturn(BigDecimal.valueOf(200));

        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        Long reservationId = reservationService.createReservation(dto);

        verify(reservationRepository).save(reservationCaptor.capture());

        Reservation saved = reservationCaptor.getValue();

        assertAll(
                () -> assertEquals(1L, reservationId),
                () -> assertEquals(existingUser, saved.getUser()),
                () -> assertEquals(car, saved.getCar()),
                () -> assertEquals(BigDecimal.valueOf(200), saved.getCost()),
                () -> assertEquals(PaymentStatus.PAID, saved.getPaymentStatus()),
                () -> assertEquals(ReservationStatus.OPEN, saved.getReservationStatus())
        );

        verify(userService, never()).createNewUser(any());
        verify(userService).getUserById(5L);
    }

    @Test
    void createReservationShouldThrowCarNotAvailableExceptionWhenCarIsOutOfStock() throws InvalidDatesException, CarNotFoundException, InvalidCarStockAmountException {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusDays(1);

        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                userDto,
                start,
                end
        );

        Car car = Car.builder()
                .id(1L)
                .inStock(0)
                .costPerDay(BigDecimal.valueOf(100))
                .build();

        when(carService.getCarById(1L)).thenReturn(car);

        assertThrows(
                CarNotAvailableException.class,
                () -> reservationService.createReservation(dto)
        );

        verify(validationService).validateReservationDates(start, end);
        verify(carService).getCarById(1L);
        verify(carService, never()).decrementCarInStock(anyLong(), anyInt());
        verifyNoInteractions(userService);
        verifyNoInteractions(reservationCostCalculatorService);
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void createReservationShouldThrowInvalidDatesException() throws InvalidDatesException {
        LocalDateTime start = LocalDateTime.of(2026, 1, 2, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 10, 0);

        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                userDto,
                start,
                end
        );

        doThrow(new InvalidDatesException("Invalid dates"))
                .when(validationService)
                .validateReservationDates(start, end);

        assertThrows(
                InvalidDatesException.class,
                () -> reservationService.createReservation(dto)
        );

        verify(validationService).validateReservationDates(start, end);
        verifyNoInteractions(carService);
        verifyNoInteractions(userService);
        verifyNoInteractions(reservationCostCalculatorService);
        verifyNoInteractions(reservationRepository);
    }

    @Test
    void createReservationShouldThrowCarNotFoundException() throws CarNotFoundException, InvalidDatesException, InvalidCarStockAmountException {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusDays(1);

        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                userDto,
                start,
                end
        );

        when(carService.getCarById(1L)).thenThrow(new CarNotFoundException(1L));

        assertThrows(
                CarNotFoundException.class,
                () -> reservationService.createReservation(dto)
        );

        verify(validationService).validateReservationDates(start, end);
        verify(carService).getCarById(1L);
        verify(carService, never()).decrementCarInStock(anyLong(), anyInt());
        verifyNoInteractions(userService);
        verifyNoInteractions(reservationCostCalculatorService);
        verifyNoInteractions(reservationRepository);
    }

    @Test
    void createReservationShouldThrowUserNotFoundException() throws CarNotFoundException, InvalidCarStockAmountException, UserNotFoundException, InvalidDatesException {
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = start.plusDays(1);

        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                userDto,
                start,
                end
        );

        Car car = Car.builder()
                .id(1L)
                .inStock(1)
                .costPerDay(BigDecimal.valueOf(100))
                .build();

        when(carService.getCarById(1L)).thenReturn(car);
        when(carService.decrementCarInStock(1L, 1)).thenReturn(car);

        when(userService.checkIfUserAlreadyExists(any(User.class))).thenReturn(Optional.of(5L));

        when(userService.getUserById(5L)).thenThrow(new UserNotFoundException(5L));

        assertThrows(
                UserNotFoundException.class,
                () -> reservationService.createReservation(dto)
        );

        verify(validationService).validateReservationDates(start, end);
        verify(carService).decrementCarInStock(1L, 1);
        verify(userService).getUserById(5L);

        verifyNoInteractions(reservationCostCalculatorService);
        verify(reservationRepository, never()).save(any());
    }
}
