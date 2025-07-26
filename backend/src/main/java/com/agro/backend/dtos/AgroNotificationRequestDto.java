package com.agro.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgroNotificationRequestDto {
    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "Booking Id is required")
    private Long bookingId;

    @NotNull(message = "Farmer Id is required")
    private Long farmerId;
}
