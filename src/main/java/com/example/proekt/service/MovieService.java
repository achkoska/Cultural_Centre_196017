package com.example.proekt.service;

import com.example.proekt.model.Art;
import com.example.proekt.model.Director;
import com.example.proekt.model.Movie;
import com.example.proekt.model.MovieGenre;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface MovieService {
    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    Optional<Movie> findByName(String name);

    Optional<Movie> save(String name, Double price, MovieGenre type, Long director, String imageUrl, String date);


    Optional<Movie> edit(Long id,String name,  Double price,MovieGenre type,Long director,String imageUrl,String date);


    Optional<Movie> like(Long id);


    List<Movie> listMoviesWithDate(String date);


    void deleteById(Long id);
}
