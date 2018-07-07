package com.webdev.spring.controller;

import com.webdev.spring.domain.User;
import com.webdev.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SecurityController {
    @Autowired
    private UserService service;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        this.service.add(user);
        return "redirect:/login";
    }
}
