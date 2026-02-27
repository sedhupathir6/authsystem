package com.example.authsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    void save(Long user);
}