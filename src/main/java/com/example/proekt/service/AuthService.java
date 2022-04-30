package com.example.proekt.service;

import com.example.proekt.model.User;

public interface AuthService {
    User login(String username, String password);
}

