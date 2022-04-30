package com.example.proekt.service.impl;

import com.example.proekt.model.*;
import com.example.proekt.model.enumerations.ReservationTicketStatus;
import com.example.proekt.model.exceptions.*;
import com.example.proekt.repository.jpa.MovieRepository;
import com.example.proekt.repository.jpa.PerformanceRepository;
import com.example.proekt.repository.jpa.ReservationTicketRepository;
import com.example.proekt.repository.jpa.UserRepository;
import com.example.proekt.service.ArtService;
import com.example.proekt.service.MovieService;
import com.example.proekt.service.PerformanceService;
import com.example.proekt.service.ReservationTicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationTicketServiceImpl implements ReservationTicketService {
    private final ReservationTicketRepository reservationTicketRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final PerformanceRepository performanceRepository;
    private final MovieService movieService;
    private final PerformanceService performanceService;

    public ReservationTicketServiceImpl(ReservationTicketRepository reservationTicketRepository, UserRepository userRepository, MovieRepository movieRepository, PerformanceRepository performanceRepository, MovieService movieService, PerformanceService performanceService) {
        this.reservationTicketRepository = reservationTicketRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.performanceRepository = performanceRepository;
        this.movieService = movieService;
        this.performanceService = performanceService;
    }

    @Override
    public List<Movie> listAllMoviesInReservationTicket(Long ticketId) {
        if(!this.reservationTicketRepository.findById(ticketId).isPresent()){
            throw new ReservationTicketNotFoundException(ticketId);
        }else {
            return ((ReservationTicket)this.reservationTicketRepository.findById(ticketId).get()).getMovies();
        }

    }

    @Override
    public List<Performance> listAllPerformancesInReservationTicket(Long ticketId) {
        if(!this.reservationTicketRepository.findById(ticketId).isPresent()){
            throw new ReservationTicketNotFoundException(ticketId);
        }else {
            return ((ReservationTicket)this.reservationTicketRepository.findById(ticketId).get()).getPerformances();
        }

    }



    @Override
    public ReservationTicket getActiveTicket(String username) {
        User user = (User)this.userRepository.findByUsername(username).orElseThrow(() -> {
            return new UserNotFoundException(username);
        });
        return (ReservationTicket) this.reservationTicketRepository.findByUserAndStatus(user, ReservationTicketStatus.CREATED).orElseGet(() -> {
            ReservationTicket ticket = new ReservationTicket(user);
            return (ReservationTicket) this.reservationTicketRepository.save(ticket);
        });
    }

    @Override
    public ReservationTicket addMovieToReservationTicket(String username, Long movieId) {
        ReservationTicket reservationTicket = this.getActiveTicket(username);
        Movie movie = (Movie) this.movieService.findById(movieId).orElseThrow(() -> {
            return new MovieNotFoundException(movieId);
        });
        if (((List)reservationTicket.getMovies().stream().filter((i) -> {
            return i.getId().equals(movieId);
        }).collect(Collectors.toList())).size() > 0) {
            throw new MovieAlreadyInReservationTicketException(movieId, username);
        } else {
            reservationTicket.getMovies().add(movie);
            return (ReservationTicket) this.reservationTicketRepository.save(reservationTicket);
        }
    }

    @Override
    public ReservationTicket addPerformanceToReservationTicket(String username, Long performanceId) {
        ReservationTicket reservationTicket = this.getActiveTicket(username);
        Performance performance = (Performance) this.performanceService.findById(performanceId).orElseThrow(() -> {
            return new PerformanceNotFoundException(performanceId);
        });
        if (((List)reservationTicket.getPerformances().stream().filter((i) -> {
            return i.getId().equals(performanceId);
        }).collect(Collectors.toList())).size() > 0) {
            throw new PerformanceAlreadyInReservationTicketException(performanceId, username);
        } else {
            reservationTicket.getPerformances().add(performance);
            return (ReservationTicket) this.reservationTicketRepository.save(reservationTicket);
        }
    }

    @Override
    public void deleteById(Long id) {
         this.reservationTicketRepository.deleteById(id);
    }

    @Override
    public Optional<ReservationTicket> findById(Long id) {
        return this.reservationTicketRepository.findById(id);
    }
}
