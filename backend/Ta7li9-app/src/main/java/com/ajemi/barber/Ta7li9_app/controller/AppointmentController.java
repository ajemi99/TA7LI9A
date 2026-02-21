package com.ajemi.barber.Ta7li9_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajemi.barber.Ta7li9_app.dto.AppointmentRequestDTO;
import com.ajemi.barber.Ta7li9_app.dto.AppointmentResponseDTO;
import com.ajemi.barber.Ta7li9_app.entity.User;
import com.ajemi.barber.Ta7li9_app.security.UserPrincipal;
import com.ajemi.barber.Ta7li9_app.service.AppointmentService;



@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    // 1. Unified Search (History + Global b phone)
    @GetMapping("/search-clients")
    public ResponseEntity<List<User>> searchClients(
        @AuthenticationPrincipal UserPrincipal currentUser,
        @RequestParam String query) {
            List<User> results = appointmentService.searchClient(currentUser.getId(), query);
             return ResponseEntity.ok(results);
        }
    
    // 2. Create Appointment (Manual wala Registered)
    @PostMapping("/add")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
        @AuthenticationPrincipal UserPrincipal currentUser,
        @RequestBody AppointmentRequestDTO dto) {
        AppointmentResponseDTO response = appointmentService.createManualAppointment(currentUser.getId(), dto);
        return ResponseEntity.ok(response);
    }
}
