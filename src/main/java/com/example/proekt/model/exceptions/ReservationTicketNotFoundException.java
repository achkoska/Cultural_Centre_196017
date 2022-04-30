package com.example.proekt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationTicketNotFoundException extends RuntimeException {
    public ReservationTicketNotFoundException(Long id) {
        super(String.format("Reservation ticket with id: %d was not found", id));
    }
}
