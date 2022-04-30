package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.Director;

import com.example.proekt.model.Performance;
import com.example.proekt.model.PerformanceGenre;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryPerformanceRepository {

    public InMemoryPerformanceRepository() {
    }

    public List<Performance> findAll() {
        return DataHolder.performances;
    }

    public Optional<Performance> findById(Long id) {
        return DataHolder.performances.stream().filter((i) -> {
            return i.getId().equals(id);
        }).findFirst();
    }

    public Optional<Performance> findByName(String name) {
        return DataHolder.performances.stream().filter((i) -> {
            return i.getName().equals(name);
        }).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.performances.removeIf((i) -> {
            return i.getId().equals(id);
        });
    }

    public Optional<Performance> save(String name, Double price, PerformanceGenre type, Director director,String imageUrl,String date) {
        DataHolder.performances.removeIf((i) -> {
            return i.getName().equals(name);
        });
        Performance performance = new Performance(name, price,  type, director,imageUrl,date);
        DataHolder.performances.add(performance);
        return Optional.of(performance);
    }
}
