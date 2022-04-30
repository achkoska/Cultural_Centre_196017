package com.example.proekt.service.impl;

import com.example.proekt.model.Artist;
import com.example.proekt.model.Director;
import com.example.proekt.repository.jpa.ArtistRepository;
import com.example.proekt.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public List<Artist> findAll() {
        return this.artistRepository.findAll();
    }

    @Override
    public Optional<Artist> save(String name) {
        return Optional.of((Artist) this.artistRepository.save(new Artist(name)));
    }

    @Override
    public void deleteById(Long id) {
        this.artistRepository.deleteById(id);

    }
}
