package com.example.authsystem.controller;

import com.example.authsystem.entity.User;
import com.example.authsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            return "redirect:/register?error";
        }

        User user = new User(
                name,
                email,
                passwordEncoder.encode(password),
                "ROLE_USER");

        userRepository.save(user);
        return "redirect:/login";
    }
}