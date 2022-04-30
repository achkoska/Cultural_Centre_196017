package com.example.proekt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtNotFoundException extends RuntimeException{
    public ArtNotFoundException(Long id) {
        super(String.format("Art with id: %d was not found", id));
    }

}
