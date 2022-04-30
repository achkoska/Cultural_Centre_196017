package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.ReservationTicket;

import com.example.proekt.model.enumerations.ReservationTicketStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryReservationTicketRepository {
    public InMemoryReservationTicketRepository() {
    }

    public Optional<ReservationTicket> findById(Long id) {
        return DataHolder.reservationTickets.stream().filter((i) -> {
            return i.getId().equals(id);
        }).findFirst();
    }

    public Optional<ReservationTicket> findByUsernameAndStatus(String username, ReservationTicketStatus status) {
        return DataHolder.reservationTickets.stream().filter((i) -> {
            return i.getUser().getUsername().equals(username) && i.getStatus().equals(status);
        }).findFirst();
    }

    public ReservationTicket save(ReservationTicket reservationTicket) {
        DataHolder.reservationTickets.removeIf((i) -> {
            return i.getUser().getUsername().equals(reservationTicket.getUser().getUsername());
        });
        DataHolder.reservationTickets.add(reservationTicket);
        return reservationTicket;
    }

}
