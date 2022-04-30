package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.Art;
import com.example.proekt.model.Artist;
import com.example.proekt.model.Director;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtRepository {
    public InMemoryArtRepository() {
    }

    public List<Art> findAll() {
        return DataHolder.arts;
    }

    public Optional<Art> findById(Long id) {
        return DataHolder.arts.stream().filter((i) -> {
            return i.getId().equals(id);
        }).findFirst();
    }

    public Optional<Art> findByName(String name) {
        return DataHolder.arts.stream().filter((i) -> {
            return i.getName().equals(name);
        }).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.arts.removeIf((i) -> {
            return i.getId().equals(id);
        });
    }

    public Optional<Art> save(String name,String description, Double price,  Artist artist,String imageUrl) {
        DataHolder.arts.removeIf((i) -> {
            return i.getName().equals(name);
        });
        Art art = new Art(name,description, price,  artist, imageUrl);
        DataHolder.arts.add(art);
        return Optional.of(art);
    }

}
