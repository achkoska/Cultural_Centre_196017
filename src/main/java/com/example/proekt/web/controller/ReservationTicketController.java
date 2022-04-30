package com.example.proekt.web.controller;

import com.example.proekt.model.Director;
import com.example.proekt.model.Movie;
import com.example.proekt.model.ReservationTicket;
import com.example.proekt.model.User;
import com.example.proekt.service.ReservationTicketService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping({"/reservation-ticket"})
public class ReservationTicketController {
    private final ReservationTicketService reservationTicketService;

    public ReservationTicketController(ReservationTicketService reservationTicketService) {
        this.reservationTicketService = reservationTicketService;
    }


    @GetMapping
    public String getReservationTicketPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = req.getRemoteUser();
        ReservationTicket reservationTicket = this.reservationTicketService.getActiveTicket(username);
        model.addAttribute("movies", this.reservationTicketService.listAllMoviesInReservationTicket(reservationTicket.getId()));
        model.addAttribute("performances", this.reservationTicketService.listAllPerformancesInReservationTicket(reservationTicket.getId()));
        model.addAttribute("bodyContent", "reservation-ticket");
        return "reservation-ticket.html";
    }

    @PostMapping({"/{id}/add-movie"})
    public String addMovieToReservationTicket(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.reservationTicketService.addMovieToReservationTicket(user.getUsername(), id);
            return "redirect:/reservation-ticket";
        } catch (RuntimeException var5) {
            return "redirect:/reservation-ticket?error=" + var5.getMessage();
        }
    }

    @PostMapping({"/{id}/add-performance"})
    public String addPerformanceToReservationTicket(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.reservationTicketService.addPerformanceToReservationTicket(user.getUsername(), id);
            return "redirect:/reservation-ticket";
        } catch (RuntimeException var5) {
            return "redirect:/reservation-ticket?error=" + var5.getMessage();
        }
    }

    @PostMapping({"/reservation-ticket/{id}/delete"})
    public String deleteReservationTicket(@PathVariable Long id) {
        this.reservationTicketService.deleteById(id);
        return "redirect:/reservation-ticket";
    }





}
