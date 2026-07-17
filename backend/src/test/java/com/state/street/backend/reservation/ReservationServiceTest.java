package com.state.street.backend.reservation;

import com.state.street.backend.repositories.ReservationRepository;
import com.state.street.backend.services.CarService;
import com.state.street.backend.services.ReservationService;
import com.state.street.backend.services.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CarService carService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReservationService reservationService;
}
