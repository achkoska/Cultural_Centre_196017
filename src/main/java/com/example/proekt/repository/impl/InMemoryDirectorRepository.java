package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.Director;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDirectorRepository {
    public InMemoryDirectorRepository() {
    }

    public List<Director> findAll() {
        return DataHolder.directors;
    }

    public Optional<Director> findById(Long id) {
        return DataHolder.directors.stream().filter((i) -> {
            return i.getId().equals(id);
        }).findFirst();
    }

    public Optional<Director> save(String name, String surname) {
        Director director = new Director(name, surname);
        DataHolder.directors.add(director);
        return Optional.of(director);
    }

    public boolean deleteById(Long id) {
        return DataHolder.directors.removeIf((i) -> {
            return i.getId().equals(id);
        });
    }

}
