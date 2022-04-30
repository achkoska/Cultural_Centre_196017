package com.example.proekt.service;

import com.example.proekt.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    Optional<Director> findById(Long id);

    List<Director> findAll();

    Optional<Director> save(String name, String surname);

    void deleteById(Long id);
}
