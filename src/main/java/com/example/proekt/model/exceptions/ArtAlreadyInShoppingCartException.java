package com.example.proekt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ArtAlreadyInShoppingCartException extends RuntimeException{
    public ArtAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Art with id: %d already exists in reservation ticket for user with username %s", id, username));
    }
}
