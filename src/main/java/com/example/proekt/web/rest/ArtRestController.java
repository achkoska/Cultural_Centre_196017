package com.example.proekt.web.rest;

import com.example.proekt.model.Art;

import com.example.proekt.service.ArtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/arts"})
public class ArtRestController {
    private final ArtService artService;

    public ArtRestController(ArtService artService) {
        this.artService = artService;
    }

    @GetMapping
    private List<Art> findAll() {
        return this.artService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Art> findById(@PathVariable Long id) {
        return (ResponseEntity)this.artService.findById(id).map((art) -> {
            return ResponseEntity.ok().body(art);
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }





    @DeleteMapping({"/{id}/delete"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.artService.deleteById(id);
        return this.artService.findById(id).isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
