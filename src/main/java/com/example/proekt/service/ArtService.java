package com.example.proekt.service;



import com.example.proekt.model.Art;

import java.util.List;
import java.util.Optional;

public interface ArtService {
    List<Art> findAll();

    Optional<Art> findById(Long id);

    Optional<Art> findByName(String name);

    Optional<Art> save(String name,String description,Double price,Long artist,String imageUrl);


    Optional<Art> edit(Long id,String name,String description, Double price,Long artist,String imageUrl);


    Optional<Art> like(Long id);


    void deleteById(Long id);

}
