package com.example.proekt.service;

import com.example.proekt.model.Art;
import com.example.proekt.model.Movie;
import com.example.proekt.model.Performance;
import com.example.proekt.model.ReservationTicket;

import java.util.List;
import java.util.Optional;

public interface ReservationTicketService {
    List<Movie> listAllMoviesInReservationTicket(Long ticketId);
    List<Performance> listAllPerformancesInReservationTicket(Long ticketId);


    ReservationTicket getActiveTicket(String username);

    ReservationTicket addMovieToReservationTicket(String username, Long movieId);
    ReservationTicket addPerformanceToReservationTicket(String username, Long performanceId);

    void deleteById(Long id);

    Optional<ReservationTicket> findById(Long id);



}
