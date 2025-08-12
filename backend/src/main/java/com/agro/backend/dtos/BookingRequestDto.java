package com.agro.backend.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDto {


    @NotNull(message = "Service ID is required")
    private Long serviceId;
    
    @NotNull(message = "Select date is required")
    private LocalDateTime preferredDate;
    private String notes;
}
