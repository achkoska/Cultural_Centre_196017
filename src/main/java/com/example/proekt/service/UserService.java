package com.example.proekt.service;

import com.example.proekt.model.Role;
import com.example.proekt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

}
