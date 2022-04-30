package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.Director;
import com.example.proekt.model.Movie;
import com.example.proekt.model.MovieGenre;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryMovieRepository {
    public InMemoryMovieRepository() {
    }

    public List<Movie> findAll() {
        return DataHolder.movies;
    }

    public Optional<Movie> findById(Long id) {
        return DataHolder.movies.stream().filter((i) -> {
            return i.getId().equals(id);
        }).findFirst();
    }

    public Optional<Movie> findByName(String name) {
        return DataHolder.movies.stream().filter((i) -> {
            return i.getName().equals(name);
        }).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.movies.removeIf((i) -> {
            return i.getId().equals(id);
        });
    }

    public Optional<Movie> save(String name, Double price, MovieGenre type, Director director, String imageUrl, String date) {
        DataHolder.movies.removeIf((i) -> {
            return i.getName().equals(name);
        });
        Movie movie = new Movie(name, price,  type, director,imageUrl,date);
        DataHolder.movies.add(movie);
        return Optional.of(movie);
    }
}
