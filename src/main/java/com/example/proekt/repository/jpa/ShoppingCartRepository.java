package com.example.proekt.repository.jpa;

import com.example.proekt.model.ReservationTicket;
import com.example.proekt.model.ShoppingCart;
import com.example.proekt.model.User;
import com.example.proekt.model.enumerations.ReservationTicketStatus;
import com.example.proekt.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}
