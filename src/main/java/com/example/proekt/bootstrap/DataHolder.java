package com.example.proekt.bootstrap;

import com.example.proekt.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users = new ArrayList();
    public static List<Movie> movies = new ArrayList();
    public static List<Performance> performances = new ArrayList();
    public static List<Art> arts = new ArrayList();


    public static List<Director> directors = new ArrayList();
    public static List<Artist> artists = new ArrayList();

    public static List<ReservationTicket> reservationTickets = new ArrayList();
    public static List<ShoppingCart> shoppingCarts = new ArrayList();


    public DataHolder() {
    }
}
