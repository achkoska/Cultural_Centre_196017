package com.example.proekt.repository.jpa;

import com.example.proekt.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance,Long> {

    Optional<Performance> findByName(String name);

    void deleteByName(String name);

    List<Performance> findByDate(String date);


}
