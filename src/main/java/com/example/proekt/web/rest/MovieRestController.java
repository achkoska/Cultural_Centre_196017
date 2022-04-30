package com.example.proekt.web.rest;

import com.example.proekt.model.Movie;
import com.example.proekt.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/api/movies"})
public class MovieRestController {
    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    private List<Movie> findAll() {
        return this.movieService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return (ResponseEntity)this.movieService.findById(id).map((movie) -> {
            return ResponseEntity.ok().body(movie);
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }





    @DeleteMapping({"/{id}/delete"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return this.movieService.findById(id).isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
