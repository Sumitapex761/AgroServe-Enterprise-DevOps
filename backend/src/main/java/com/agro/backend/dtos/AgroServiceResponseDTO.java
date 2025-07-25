package com.agro.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgroServiceResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long providerId;  // We send only provider id, not full provider details
}
