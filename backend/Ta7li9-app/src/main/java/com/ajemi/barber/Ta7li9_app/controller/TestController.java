package com.ajemi.barber.Ta7li9_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user-data")
    public String getUserData() {
        return "Ahlan A.J.! Had l-ma3loumat m7miya w nta dkhlti liha hit 3ndk Token s7i7.";
    }
}