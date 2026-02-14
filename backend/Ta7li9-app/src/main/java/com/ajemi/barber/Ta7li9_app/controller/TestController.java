package com.ajemi.barber.Ta7li9_app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajemi.barber.Ta7li9_app.security.UserPrincipal;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user-data")
    public String getUserData() {
        return "Ahlan A.J.! Had l-ma3loumat m7miya w nta dkhlti liha hit 3ndk Token s7i7.";
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal user) {
        // Daba 3ndk l-access l-kolchi bla ma t-mchi l-DB
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("email", user.getEmail());
        data.put("role", user.getRole());
        
        return ResponseEntity.ok(data);
    }
}