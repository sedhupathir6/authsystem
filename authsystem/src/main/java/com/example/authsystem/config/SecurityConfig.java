package com.example.authsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/").authenticated()
                                                .anyRequest().permitAll())
                                .formLogin(form -> form
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())
                                .logout(logout -> logout.permitAll());

                return http.build();
        }
}