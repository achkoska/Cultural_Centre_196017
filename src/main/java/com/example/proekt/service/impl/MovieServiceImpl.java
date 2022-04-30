package com.example.proekt.service.impl;

import com.example.proekt.model.Art;
import com.example.proekt.model.Director;
import com.example.proekt.model.Movie;
import com.example.proekt.model.MovieGenre;
import com.example.proekt.model.exceptions.ArtNotFoundException;
import com.example.proekt.model.exceptions.DirectorNotFoundException;
import com.example.proekt.model.exceptions.MovieNotFoundException;
import com.example.proekt.repository.jpa.DirectorRepository;
import com.example.proekt.repository.jpa.MovieRepository;
import com.example.proekt.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return this.movieRepository.findById(id);    }

    @Override
    public Optional<Movie> findByName(String name) {
        return this.movieRepository.findByName(name);    }

    @Override
    public Optional<Movie> save(String name, Double price, MovieGenre type, Long directorId, String imageUrl, String date) {

        Director director = (Director)this.directorRepository.findById(directorId).orElseThrow(()->{
            return new DirectorNotFoundException(directorId);
        });
        this.movieRepository.deleteByName(name);
        return Optional.of((Movie)this.movieRepository.save(new Movie(name,price,type,director,imageUrl,date)));

    }

    @Override
    public Optional<Movie> edit(Long id, String name, Double price, MovieGenre type, Long directorId,String imageUrl,String date) {
        Movie movie = (Movie)this.movieRepository.findById(id).orElseThrow(()->{
            return new MovieNotFoundException(id);
        });
        movie.setName(name);
        movie.setPrice(price);
        movie.setType(type);
        Director director = (Director) this.directorRepository.findById(directorId).orElseThrow(()->{
            return new DirectorNotFoundException(directorId);
        });
        movie.setDirector(director);
        movie.setImageUrl(imageUrl);
        movie.setDate(date);
        return Optional.of((Movie) this.movieRepository.save(movie));

    }

    @Override
    public Optional<Movie> like(Long id) {
        Movie movie = (Movie) this.movieRepository.findById(id).orElseThrow(()->{
            return new MovieNotFoundException(id);
        });
        movie.setLikes(movie.getLikes()+1);
        return Optional.of((Movie) this.movieRepository.save(movie));    }

    @Override
    public void deleteById(Long id) {

        this.movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> listMoviesWithDate(String date) {
        if(date!=null)
        {
            return movieRepository.findByDate(date);
        }
        else {
            return movieRepository.findAll();
        }
    }
}
