package com.example.proekt.service.impl;

import com.example.proekt.model.Role;
import com.example.proekt.model.User;
import com.example.proekt.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.proekt.model.exceptions.PasswordsDoNotMatchException;

import com.example.proekt.model.exceptions.UsernameAlreadyExistsException;
import com.example.proekt.repository.jpa.UserRepository;
import com.example.proekt.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) this.userRepository.findByUsername(s).orElseThrow(() -> {
            return    new UsernameNotFoundException(s);
        });
    }



    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            if (!password.equals(repeatPassword)) {
                throw new PasswordsDoNotMatchException();
            } else if (this.userRepository.findByUsername(username).isPresent()) {
                throw new UsernameAlreadyExistsException(username);
            } else {
                User user = new User(username, this.passwordEncoder.encode(password), name, surname, userRole);
                return (User) this.userRepository.save(user);
            }
        } else {
            throw new InvalidUsernameOrPasswordException();
        }
    }
}
