package com.example.proekt.service.impl;

import com.example.proekt.model.Director;
import com.example.proekt.repository.jpa.DirectorRepository;
import com.example.proekt.service.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Optional<Director> findById(Long id) {
        return this.directorRepository.findById(id);    }

    @Override
    public List<Director> findAll() {
        return this.directorRepository.findAll();
    }

    @Override
    public Optional<Director> save(String name, String surname) {
        return Optional.of((Director) this.directorRepository.save(new Director(name, surname)));
    }

    @Override
    public void deleteById(Long id) {
        this.directorRepository.deleteById(id);

    }
}
