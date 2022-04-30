package com.example.proekt.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PerformanceNotFoundException extends RuntimeException {

    public PerformanceNotFoundException(Long id) {
        super(String.format("Performance with id: %d was not found", id));
    }

}
