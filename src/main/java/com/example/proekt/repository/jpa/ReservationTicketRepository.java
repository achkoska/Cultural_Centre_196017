package com.example.proekt.repository.jpa;

import com.example.proekt.model.ReservationTicket;
import com.example.proekt.model.User;
import com.example.proekt.model.enumerations.ReservationTicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationTicketRepository extends JpaRepository<ReservationTicket,Long> {
    Optional<ReservationTicket> findByUserAndStatus(User user, ReservationTicketStatus status);

}
