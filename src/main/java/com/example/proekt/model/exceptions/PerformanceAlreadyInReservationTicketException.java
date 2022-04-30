package com.example.proekt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PerformanceAlreadyInReservationTicketException extends RuntimeException {
    public PerformanceAlreadyInReservationTicketException(Long id, String username) {
        super(String.format("Performance with id: %d already exists in reservation ticket for user with username %s", id, username));
    }
}
