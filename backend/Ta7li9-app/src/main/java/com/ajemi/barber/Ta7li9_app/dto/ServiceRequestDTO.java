package com.ajemi.barber.Ta7li9_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServiceRequestDTO {

    @NotBlank(message = "Service name is required")
    private String name;

    @Min(value = 0, message = "Price must be positive if provided")
    private Double price; 

    @Min(value = 5, message = "Duration must be at least 5 minutes")
    private Integer duration;
}
