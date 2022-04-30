package com.example.proekt.service.impl;

import com.example.proekt.model.*;
import com.example.proekt.model.exceptions.ArtNotFoundException;
import com.example.proekt.model.exceptions.DirectorNotFoundException;
import com.example.proekt.model.exceptions.MovieNotFoundException;
import com.example.proekt.model.exceptions.PerformanceNotFoundException;
import com.example.proekt.repository.jpa.DirectorRepository;
import com.example.proekt.repository.jpa.PerformanceRepository;
import com.example.proekt.service.PerformanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final DirectorRepository directorRepository;

    public PerformanceServiceImpl(PerformanceRepository performanceRepository, DirectorRepository directorRepository) {
        this.performanceRepository = performanceRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Performance> findAll() {
        return this.performanceRepository.findAll();
    }

    @Override
    public Optional<Performance> findById(Long id) {
        return this.performanceRepository.findById(id);
    }

    @Override
    public Optional<Performance> findByName(String name) {
        return this.performanceRepository.findByName(name);
    }

    @Override
    public Optional<Performance> save(String name, Double price, PerformanceGenre type, Long directorId,String imageUrl,String date) {
        Director director = (Director) this.directorRepository.findById(directorId).orElseThrow(()->{
            return new DirectorNotFoundException(directorId);
        });
        this.performanceRepository.deleteByName(name);
        return Optional.of((Performance) this.performanceRepository.save(new Performance(name,price,type,director,imageUrl,date)));
    }

    @Override
    public Optional<Performance> edit(Long id, String name, Double price, PerformanceGenre type, Long directorId,String imageUrl,String date) {
        Performance performance = (Performance) this.performanceRepository.findById(id).orElseThrow(()->{
            return new PerformanceNotFoundException(id);
        });
        performance.setName(name);
        performance.setPrice(price);
        performance.setType(type);
        Director director = (Director) this.directorRepository.findById(directorId).orElseThrow(()->{
            return new DirectorNotFoundException(directorId);
        });
        performance.setDirector(director);
        performance.setImageUrl(imageUrl);
        performance.setDate(date);
        return Optional.of((Performance) this.performanceRepository.save(performance));
    }

    @Override
    public Optional<Performance> like(Long id) {
        Performance performance = (Performance) this.performanceRepository.findById(id).orElseThrow(()->{
            return new PerformanceNotFoundException(id);
        });
        performance.setLikes(performance.getLikes()+1);
        return Optional.of((Performance) this.performanceRepository.save(performance));
    }

    @Override
    public List<Performance> listMoviesWithDate(String date) {
        if(date!=null)
        {
            return performanceRepository.findByDate(date);
        }
        else {
            return performanceRepository.findAll();
        }
    }

    @Override
    public void deleteById(Long id) {
        this.performanceRepository.deleteById(id);

    }
}
