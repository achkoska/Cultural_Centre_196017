package com.example.proekt.service;

import com.example.proekt.model.*;

import java.util.List;

public interface ShoppingCartService {
    List<Art> listAllArtsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addArtToShoppingCart(String username, Long artId);
   }
