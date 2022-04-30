package com.example.proekt.service.impl;

import com.example.proekt.model.Art;
import com.example.proekt.model.ShoppingCart;
import com.example.proekt.model.User;
import com.example.proekt.model.enumerations.ShoppingCartStatus;
import com.example.proekt.model.exceptions.ArtAlreadyInShoppingCartException;
import com.example.proekt.model.exceptions.ArtNotFoundException;
import com.example.proekt.model.exceptions.ShoppingCartNotFoundException;
import com.example.proekt.model.exceptions.UserNotFoundException;
import com.example.proekt.repository.jpa.ShoppingCartRepository;
import com.example.proekt.repository.jpa.UserRepository;
import com.example.proekt.service.ArtService;
import com.example.proekt.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ArtService artService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ArtService artService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.artService = artService;
    }

    public List<Art> listAllArtsInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent()) {
            throw new ShoppingCartNotFoundException(cartId);
        } else {
            return ((ShoppingCart)this.shoppingCartRepository.findById(cartId).get()).getArts();
        }
    }

    public ShoppingCart getActiveShoppingCart(String username) {
        User user = (User) this.userRepository.findByUsername(username).orElseThrow(() -> {
            return new UserNotFoundException(username);
        });
        return (ShoppingCart) this.shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.CREATED).orElseGet(() -> {
            ShoppingCart cart = new ShoppingCart(user);
            return (ShoppingCart)this.shoppingCartRepository.save(cart);
        });
    }

    public ShoppingCart addArtToShoppingCart(String username, Long artId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Art art = (Art) this.artService.findById(artId).orElseThrow(() -> {
            return new ArtNotFoundException(artId);
        });
        if (((List)shoppingCart.getArts().stream().filter((i) -> {
            return i.getId().equals(artId);
        }).collect(Collectors.toList())).size() > 0) {
            throw new ArtAlreadyInShoppingCartException(artId, username);
        } else {
            shoppingCart.getArts().add(art);
            return (ShoppingCart)this.shoppingCartRepository.save(shoppingCart);
        }
    }
}
