package com.example.proekt.service;

import com.example.proekt.model.*;

import java.util.List;
import java.util.Optional;

public interface PerformanceService {
    List<Performance> findAll();

    Optional<Performance> findById(Long id);

    Optional<Performance> findByName(String name);

    Optional<Performance> save(String name, Double price, PerformanceGenre type, Long director,String imageUrl,String date);


    Optional<Performance> edit(Long id,String name,  Double price,PerformanceGenre type,Long director,String imageUrl,String date);



    Optional<Performance> like(Long id);


    List<Performance> listMoviesWithDate(String date);



    void deleteById(Long id);
}
