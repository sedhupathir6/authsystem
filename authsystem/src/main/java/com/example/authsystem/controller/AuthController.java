package com.example.authsystem.controller;

import com.example.authsystem.entity.User;
import com.example.authsystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerPage(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        User user = new User(
                name,
                email,
                passwordEncoder.encode(password),
                "ROLE_USER" // default role
        );

        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
