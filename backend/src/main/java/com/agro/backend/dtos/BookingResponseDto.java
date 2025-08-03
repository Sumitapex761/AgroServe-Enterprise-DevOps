package com.agro.backend.dtos;

import java.time.LocalDateTime;

import com.agro.backend.entities.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingResponseDto {
    private Long id;
    private LocalDateTime bookingTime;
    private Status status;
    private Long userId;
    private Long serviceId;
}
