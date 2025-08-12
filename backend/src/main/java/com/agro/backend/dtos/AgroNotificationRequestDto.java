package com.agro.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AgroNotificationRequestDto {
    @NotNull
    private Long bookingId;

    @NotNull
    private Long recipientId;

    @NotNull
    private String message;
    
    @NotNull
    private String recipientType;  // "PROVIDER" or "USER"
}
