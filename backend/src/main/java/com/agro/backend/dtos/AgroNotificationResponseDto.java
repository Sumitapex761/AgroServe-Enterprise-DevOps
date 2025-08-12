package com.agro.backend.dtos;

import java.time.LocalDateTime;

import com.agro.backend.entities.RecipientType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AgroNotificationResponseDto {
    private Long id;
    private Long bookingId;
    private Long recipientId;
    private String recipientType;
    private String message;
    private LocalDateTime sentAt;
    private Boolean isRead;  // add this for frontend convenience
}