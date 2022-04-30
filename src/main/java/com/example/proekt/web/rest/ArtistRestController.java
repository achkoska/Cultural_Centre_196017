package com.example.proekt.web.rest;

import com.example.proekt.model.Artist;
import com.example.proekt.model.Director;
import com.example.proekt.service.ArtistService;
import com.example.proekt.service.DirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/artists"})
public class ArtistRestController {

    private final ArtistService artistService;

    public ArtistRestController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> findAll() {
        return this.artistService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Artist> findById(@PathVariable Long id) {
        return (ResponseEntity)this.artistService.findById(id).map((artist) -> {
            return ResponseEntity.ok().body(artist);
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping({"/add"})
    public ResponseEntity<Artist> save(@RequestParam String name) {
        return (ResponseEntity)this.artistService.save(name).map((artist) -> {
            return ResponseEntity.ok().body(artist);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @DeleteMapping({"/{id}/delete"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.artistService.deleteById(id);
        return this.artistService.findById(id).isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}
