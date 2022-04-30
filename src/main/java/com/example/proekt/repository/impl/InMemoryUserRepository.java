package com.example.proekt.repository.impl;

import com.example.proekt.bootstrap.DataHolder;
import com.example.proekt.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public InMemoryUserRepository() {
    }

    public Optional<User> findByUsername(String username) {
        return DataHolder.users.stream().filter((r) -> {
            return r.getUsername().equals(username);
        }).findFirst();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.users.stream().filter((r) -> {
            return r.getUsername().equals(username) && r.getPassword().equals(password);
        }).findFirst();
    }

    public User saveOrUpdate(User user) {
        DataHolder.users.removeIf((r) -> {
            return r.getUsername().equals(user.getUsername());
        });
        DataHolder.users.add(user);
        return user;
    }

    public void delete(String username) {
        DataHolder.users.removeIf((r) -> {
            return r.getUsername().equals(username);
        });
    }
}
