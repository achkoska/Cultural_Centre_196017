package com.example.proekt.web.controller;

import com.example.proekt.model.*;
import com.example.proekt.service.ArtService;
import com.example.proekt.service.ArtistService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtController {
    private final ArtService artService;
    private final ArtistService artistService;

    public ArtController(ArtService artService, ArtistService artistService) {
        this.artService = artService;
        this.artistService = artistService;
    }


    @GetMapping({"/arts"})
    public String getArtPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Art> arts = this.artService.findAll();
        model.addAttribute("arts", arts);
        model.addAttribute("bodyContent", "arts");
        return "arts.html";
    }

    @PostMapping({"/arts/{id}/delete"})
    public String deleteArt(@PathVariable Long id) {
        this.artService.deleteById(id);
        return "redirect:/arts";
    }

    @GetMapping({"/arts/{id}/edit-form"})
    public String editArtPage(@PathVariable Long id, Model model) {
        if (this.artService.findById(id).isPresent()) {
            Art art = (Art) this.artService.findById(id).get();
            List<Artist> artists = this.artistService.findAll();
            model.addAttribute("artists", artists);
            model.addAttribute("art", art);
            model.addAttribute("bodyContent", "add-art");
            return "edit-art.html";
        } else {
            return "redirect:/art?error=ArtNotFound";
        }
    }

    @GetMapping({"/arts/add-form"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addArtPage(Model model) {
        List<Artist> artists = this.artistService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("bodyContent", "add-art");
        return "add-art.html";
    }

    @PostMapping({"/arts/add"})
    public String saveArt(@RequestParam(required = false) Long id, @RequestParam String name, @RequestParam String description,@RequestParam Double price,  @RequestParam Long artist,@RequestParam String imageUrl) {
        if (id != null) {
            this.artService.edit(id, name, description, price, artist,imageUrl);
        } else {
            this.artService.save(name, description,  price, artist,imageUrl);
        }

        return "redirect:/arts";

    }

    @PostMapping("/arts/{id}")
    public String edit(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam Double price,
                         @RequestParam Long artist,
                         @RequestParam String imageUrl) {
        this.artService.edit(id, name, description, price,  artist,imageUrl);
        return "redirect:/arts";
    }

    @PostMapping("/arts/{id}/like")
    public String like(@PathVariable Long id) {
        this.artService.like(id);
        return "redirect:/arts";    }


}
