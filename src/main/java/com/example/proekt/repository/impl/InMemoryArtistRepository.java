package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {
    public InMemoryArtistRepository() {
    }

    public List<Artist> findAll() {
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id) {
        return DataHolder.artists.stream().filter((i) -> {
            return i.getId().equals(id);
        }).findFirst();
    }

    public Optional<Artist> save(String name) {
        Artist artist = new Artist(name);
        DataHolder.artists.add(artist);
        return Optional.of(artist);
    }

    public boolean deleteById(Long id) {
        return DataHolder.artists.removeIf((i) -> {
            return i.getId().equals(id);
        });
    }
}
