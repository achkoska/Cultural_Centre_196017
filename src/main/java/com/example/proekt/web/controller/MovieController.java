package com.example.proekt.web.controller;

import com.example.proekt.model.Director;
import com.example.proekt.model.Movie;
import com.example.proekt.model.MovieGenre;
import com.example.proekt.model.PerformanceGenre;
import com.example.proekt.service.DirectorService;
import com.example.proekt.service.MovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class MovieController {
    private final MovieService movieService;
    private final DirectorService directorService;

    public MovieController(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
    }




    @GetMapping({"/movies"})
    public String getMoviePage(@RequestParam(required = false) String error,@RequestParam(required = false) String date, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Movie> movies = this.movieService.findAll();
        if (date == null ) {
            movies= this.movieService.findAll();
        } else {
            movies= this.movieService.listMoviesWithDate(date);

        }
        model.addAttribute("movies", movies);
        model.addAttribute("bodyContent", "movies");
        return "movies.html";
    }



    @PostMapping({"/movies/{id}/delete"})
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping({"/movies/{id}/edit-form"})
    public String editMoviePage(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = (Movie) this.movieService.findById(id).get();
            List<Director> directors = this.directorService.findAll();
            model.addAttribute("directors", directors);
            model.addAttribute("movie", movie);
            model.addAttribute("bodyContent", "add-movie");
            return "edit-movie.html";
        } else {
            return "redirect:/movies?error=MovieNotFound";
        }
    }

    @GetMapping({"/movies/add-form"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addMoviePage(Model model) {
        List<Director> directors = this.directorService.findAll();
        model.addAttribute("directors", directors);
        model.addAttribute("bodyContent", "add-movie");
        return "add-movie.html";
    }

    @PostMapping({"/movies/add"})
    public String saveMovie(@RequestParam(required = false) Long id,
                            @RequestParam String name,
                            @RequestParam Double price,
                            @RequestParam MovieGenre type,
                            @RequestParam Long director,
                            @RequestParam String imageUrl,
                            @RequestParam String date) {
        if (id != null) {
            this.movieService.edit(id, name, price, type, director,imageUrl,date);
        } else {
            this.movieService.save(name, price,  type, director,imageUrl,date);
        }

        return "redirect:/movies";
    }



    @PostMapping("/movies/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam String name,
                       @RequestParam Double price,
                       @RequestParam MovieGenre type,
                       @RequestParam Long director,
                       @RequestParam String imageUrl,
                        @RequestParam String date) {
        this.movieService.edit(id, name, price, type,  director,imageUrl,date);
        return "redirect:/movies";
    }

    @PostMapping("/movies/{id}/like")
    public String like(@PathVariable Long id) {
        this.movieService.like(id);
        return "redirect:/movies";    }


}
