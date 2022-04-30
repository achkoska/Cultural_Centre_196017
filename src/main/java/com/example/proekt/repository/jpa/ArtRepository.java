package com.example.proekt.repository.jpa;

import com.example.proekt.model.Art;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtRepository extends JpaRepository<Art,Long> {
    Optional<Art> findByName(String name);

    void deleteByName(String name);



}
