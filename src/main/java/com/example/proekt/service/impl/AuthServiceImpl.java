package com.example.proekt.service.impl;

import com.example.proekt.model.User;
import com.example.proekt.model.exceptions.InvalidArgumentsException;
import com.example.proekt.model.exceptions.InvalidUserCredentialsException;
import com.example.proekt.repository.jpa.UserRepository;
import com.example.proekt.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            return (User)this.userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
        } else {
            throw new InvalidArgumentsException();
        }
    }
}
