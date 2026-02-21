package com.ajemi.barber.Ta7li9_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajemi.barber.Ta7li9_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    
}
