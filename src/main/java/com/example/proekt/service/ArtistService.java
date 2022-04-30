package com.example.proekt.service;



import com.example.proekt.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Optional<Artist> findById(Long id);

    List<Artist> findAll();

    Optional<Artist> save(String name);

    void deleteById(Long id);
}
