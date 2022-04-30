package com.example.proekt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    public HomeController() {
    }

    @GetMapping({"/","/home"})
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
        return "home.html";
    }

    @GetMapping({"/access_denied"})
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "access_denied.html";
    }
}
