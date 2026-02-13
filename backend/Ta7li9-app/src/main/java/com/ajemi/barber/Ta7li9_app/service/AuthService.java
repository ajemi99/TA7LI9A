package com.ajemi.barber.Ta7li9_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ajemi.barber.Ta7li9_app.dto.RequestRegister;
import com.ajemi.barber.Ta7li9_app.entity.User;
import com.ajemi.barber.Ta7li9_app.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void register(RequestRegister body) {
        if (userRepository.existsByEmail(body.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (body.getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Ma-ymknch t-creer Admin mn hna!");
        }
        
        User user = new User();
        String role = body.getRole().toUpperCase();
        if (role.equals("CLIENT") || role.equals("COIFFEUR")) {
            user.setRole("ROLE_" + role);
        } else {
            user.setRole("ROLE_CLIENT"); // Default role
        }
        
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setEmail(body.getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(user);
    }
}