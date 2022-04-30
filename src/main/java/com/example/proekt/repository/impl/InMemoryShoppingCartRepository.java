package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.ShoppingCart;
import com.example.proekt.model.enumerations.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {
    public InMemoryShoppingCartRepository() {
    }

    public Optional<ShoppingCart> findById(Long id) {
        return DataHolder.shoppingCarts.stream().filter((i) -> {
            return i.getId().equals(id);
        }).findFirst();
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status) {
        return DataHolder.shoppingCarts.stream().filter((i) -> {
            return i.getUser().getUsername().equals(username) && i.getStatus().equals(status);
        }).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        DataHolder.shoppingCarts.removeIf((i) -> {
            return i.getUser().getUsername().equals(shoppingCart.getUser().getUsername());
        });
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }
}
