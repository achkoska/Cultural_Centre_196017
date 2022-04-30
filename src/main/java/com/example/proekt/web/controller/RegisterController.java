package com.example.proekt.web.controller;

import com.example.proekt.model.Role;
import com.example.proekt.model.exceptions.InvalidArgumentsException;
import com.example.proekt.model.exceptions.PasswordsDoNotMatchException;
import com.example.proekt.service.AuthService;
import com.example.proekt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/register"})
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("bodyContent", "register");
        return "register.html";
    }

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String repeatedPassword, @RequestParam String name, @RequestParam String surname, @RequestParam Role role) {
        try {
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidArgumentsException var8) {
            return "redirect:/register?error=" + var8.getMessage();
        }
    }
}
