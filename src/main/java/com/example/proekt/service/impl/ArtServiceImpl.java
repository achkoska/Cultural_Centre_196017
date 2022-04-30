package com.example.proekt.service.impl;

import com.example.proekt.model.Art;
import com.example.proekt.model.Artist;
import com.example.proekt.model.Director;
import com.example.proekt.model.Movie;
import com.example.proekt.model.exceptions.ArtNotFoundException;
import com.example.proekt.model.exceptions.ArtistNotFoundException;
import com.example.proekt.model.exceptions.DirectorNotFoundException;
import com.example.proekt.model.exceptions.MovieNotFoundException;
import com.example.proekt.repository.jpa.ArtRepository;
import com.example.proekt.repository.jpa.ArtistRepository;
import com.example.proekt.service.ArtService;
import com.example.proekt.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtServiceImpl implements ArtService {

    private final ArtRepository artRepository;
    private final ArtistService artistService;
    private final ArtistRepository artistRepository;

    public ArtServiceImpl(ArtRepository artRepository, ArtistService artistService, ArtistRepository artistRepository) {
        this.artRepository = artRepository;
        this.artistService = artistService;
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Art> findAll() {
        return this.artRepository.findAll();
    }

    @Override
    public Optional<Art> findById(Long id) {
        return this.artRepository.findById(id);
    }

    @Override
    public Optional<Art> findByName(String name) {
        return this.artRepository.findByName(name);
    }

    @Override
    public Optional<Art> save(String name, String description, Double price, Long artistId,String imageUrl) {
        Artist artist = (Artist) this.artistRepository.findById(artistId).orElseThrow(()->{
            return new ArtistNotFoundException(artistId);
        });
        this.artRepository.deleteByName(name);
        return Optional.of((Art) this.artRepository.save(new Art(name,description,price,artist,imageUrl)));
    }

    @Override
    public Optional<Art> edit(Long id, String name, String description, Double price, Long artistId,String imageUrl) {
        Art art = (Art) this.artRepository.findById(id).orElseThrow(()->{
            return new ArtNotFoundException(id);
        });
        art.setName(name);
        art.setDescription(description);
        art.setPrice(price);
        Artist artist = (Artist) this.artistRepository.findById(artistId).orElseThrow(()->{
            return new ArtistNotFoundException(artistId);
        });
        art.setArtist(artist);
        art.setImageUrl(imageUrl);
        return Optional.of((Art) this.artRepository.save(art));    }

    @Override
    public Optional<Art> like(Long id) {
        Art art = (Art) this.artRepository.findById(id).orElseThrow(()->{
            return new ArtNotFoundException(id);
        });
        art.setLikes(art.getLikes()+1);
        return Optional.of((Art) this.artRepository.save(art));
    }

    @Override
    public void deleteById(Long id) {
        this.artRepository.deleteById(id);

    }
}
