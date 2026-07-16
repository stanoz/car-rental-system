package com.state.street.backend.exceptions;

import com.state.street.backend.exceptions.reservation.InvalidDatesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ReservationExceptionHandler {
    @ExceptionHandler(InvalidDatesException.class)
    public ResponseEntity<String> handleInvalidDatesException(InvalidDatesException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
