package com.state.street.backend.reservation;

import com.state.street.backend.controllers.ReservationController;
import com.state.street.backend.model.dto.CreateReservationDto;
import com.state.street.backend.model.dto.UserDto;
import com.state.street.backend.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReservationService reservationService;

    @Test
    void createReservationShouldReturnBadRequestWhenValidationFails() throws Exception {

        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);

        UserDto user = UserDto.builder()
                .firstName("")
                .lastName("Doe")
                .drivingLicenseId("")
                .emailAddress("wrong-email")
                .phoneNumber("123")
                .build();

        CreateReservationDto dto = new CreateReservationDto(
                null,
                user,
                start,
                start
        );

        mockMvc.perform(post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.carId").value("carId cannot be null."))
                .andExpect(jsonPath("$.['user.firstName']").value("First name cannot be blank."))
                .andExpect(jsonPath("$.['user.drivingLicenseId']").value("Driving license ID cannot be blank."))
                .andExpect(jsonPath("$.['user.emailAddress']").value("Email address must be valid."))
                .andExpect(jsonPath("$.['user.phoneNumber']").value("Phone number must consist of exactly 9 digits."));

        verifyNoInteractions(reservationService);
    }
}
