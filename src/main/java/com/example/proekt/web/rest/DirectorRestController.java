package com.example.proekt.web.rest;

import com.example.proekt.model.Director;
import com.example.proekt.service.DirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/directors"})
public class DirectorRestController {
    private final DirectorService directorService;

    public DirectorRestController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<Director> findAll() {
        return this.directorService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Director> findById(@PathVariable Long id) {
        return (ResponseEntity)this.directorService.findById(id).map((director) -> {
            return ResponseEntity.ok().body(director);
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping({"/add"})
    public ResponseEntity<Director> save(@RequestParam String name, @RequestParam String surname) {
        return (ResponseEntity)this.directorService.save(name, surname).map((director) -> {
            return ResponseEntity.ok().body(director);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @DeleteMapping({"/{id}/delete"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.directorService.deleteById(id);
        return this.directorService.findById(id).isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}
