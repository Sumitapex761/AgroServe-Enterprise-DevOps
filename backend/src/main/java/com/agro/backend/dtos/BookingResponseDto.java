package com.agro.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {
    private Long id;
    private String status;
    private LocalDateTime preferredDate;
    private String notes;
    private String serviceName;
    private String userName;
}

