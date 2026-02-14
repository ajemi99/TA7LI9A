package com.ajemi.barber.Ta7li9_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private String duration;
    private String coiffeurName; 
}