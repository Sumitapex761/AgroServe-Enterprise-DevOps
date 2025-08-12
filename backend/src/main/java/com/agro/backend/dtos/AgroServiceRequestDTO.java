package com.agro.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgroServiceRequestDTO {

    @NotBlank(message = "Service name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @NotNull(message = "Provider id is required")
    private Long providerId;  // For linking with AgroServiceProvider
}
