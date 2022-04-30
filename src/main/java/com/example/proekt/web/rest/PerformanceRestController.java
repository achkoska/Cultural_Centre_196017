package com.example.proekt.web.rest;

import com.example.proekt.model.Performance;

import com.example.proekt.service.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/api/performances"})
public class PerformanceRestController {
    private final PerformanceService performanceService;

    public PerformanceRestController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping
    private List<Performance> findAll() {
        return this.performanceService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Performance> findById(@PathVariable Long id) {
        return (ResponseEntity)this.performanceService.findById(id).map((performance) -> {
            return ResponseEntity.ok().body(performance);
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }





    @DeleteMapping({"/{id}/delete"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.performanceService.deleteById(id);
        return this.performanceService.findById(id).isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
