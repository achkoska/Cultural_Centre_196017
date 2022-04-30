package com.example.proekt.model;

import com.example.proekt.model.enumerations.ReservationTicketStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ReservationTicket {

    public ReservationTicket() {
    }

    public ReservationTicket(LocalDateTime dateCreated, User user, List<Movie> movies, List<Performance> performances, ReservationTicketStatus status) {
        this.dateCreated = dateCreated;
        this.user = user;
        this.movies = movies;
        this.performances = performances;
        this.status = status;
    }

    public ReservationTicket(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.movies = new ArrayList();
        this.performances = new ArrayList();

        this.status = ReservationTicketStatus.CREATED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;
    @ManyToMany
    private List<Movie> movies;
    @ManyToMany
    private List<Performance> performances;



    @Enumerated(EnumType.STRING)
    private ReservationTicketStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public ReservationTicketStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationTicketStatus status) {
        this.status = status;
    }




    public String toString() {
        Long var10000 = this.getId();
        return "ReservationTicket(id=" + var10000 + ", dateCreated=" + this.getDateCreated() + ", user=" + this.getUser() + ", movies=" + this.getMovies() + ", performances=" + this.getPerformances() + ", status=" + this.getStatus() + ")";
    }

}
