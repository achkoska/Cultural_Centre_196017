package com.example.proekt.repository.jpa;

import com.example.proekt.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    Optional<Movie> findByName(String name);

    void deleteByName(String name);

    List<Movie> findByDate(String date);



}
