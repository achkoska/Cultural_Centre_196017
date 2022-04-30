package com.example.proekt.web.controller;

import com.example.proekt.model.*;
import com.example.proekt.service.DirectorService;
import com.example.proekt.service.MovieService;
import com.example.proekt.service.PerformanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PerformanceConttroler {
    private final PerformanceService performanceService;
    private final DirectorService directorService;

    public PerformanceConttroler(PerformanceService performanceService, DirectorService directorService) {
        this.performanceService = performanceService;
        this.directorService = directorService;
    }

    @GetMapping({"/performances"})
    public String getPerformancePage(@RequestParam(required = false) String error,@RequestParam(required = false) String date, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Performance> performances = this.performanceService.findAll();

        if (date == null ) {
            performances= this.performanceService.findAll();
        } else {
            performances= this.performanceService.listMoviesWithDate(date);

        }
        model.addAttribute("performances", performances);
        model.addAttribute("bodyContent", "performances");
        return "performances.html";
    }

    @PostMapping({"/performances/{id}/delete"})
    public String deleteProduct(@PathVariable Long id) {
        this.performanceService.deleteById(id);
        return "redirect:/performances";
    }

    @GetMapping({"/performances/{id}/edit-form"})
    public String editPerformancePage(@PathVariable Long id, Model model) {
        if (this.performanceService.findById(id).isPresent()) {
            Performance performance = (Performance) this.performanceService.findById(id).get();
            List<Director> directors = this.directorService.findAll();
            model.addAttribute("directors", directors);
            model.addAttribute("performance", performance);
            model.addAttribute("bodyContent", "add-performance");
            return "edit-performance.html";
        } else {
            return "redirect:/performance?error=PerformanceNotFound";
        }
    }

    @GetMapping({"/performances/add-form"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addPerformancePage(Model model) {
        List<Director> directors = this.directorService.findAll();
        model.addAttribute("directors", directors);
        model.addAttribute("bodyContent", "add-performance");
        return "add-performance.html";
    }

    @PostMapping({"/performances/add"})
    public String savePerformance(@RequestParam(required = false) Long id,
                                  @RequestParam String name,
                                  @RequestParam Double price,
                                  @RequestParam PerformanceGenre type,
                                  @RequestParam Long director,
                                  @RequestParam String imageUrl,
                                  @RequestParam String date) {
        if (id != null) {
            this.performanceService.edit(id, name, price, type, director,imageUrl,date);
        } else {
            this.performanceService.save(name, price,  type, director,imageUrl,date);
        }

        return "redirect:/performances";
    }

    @PostMapping("/performances/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam String name,
                       @RequestParam Double price,
                       @RequestParam PerformanceGenre type,
                       @RequestParam Long director,
                       @RequestParam String imageUrl,
                       @RequestParam String date) {
        this.performanceService.edit(id, name, price, type,  director,imageUrl,date);
        return "redirect:/performances";
    }

    @PostMapping("/performances/{id}/like")
    public String like(@PathVariable Long id) {
        this.performanceService.like(id);
        return "redirect:/performances";    }
}
