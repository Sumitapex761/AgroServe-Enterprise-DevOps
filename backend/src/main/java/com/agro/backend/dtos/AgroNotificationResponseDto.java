package com.agro.backend.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgroNotificationResponseDto {
    private Long id;
    private String message;
    private LocalDateTime sentAt;
    private Long bookingId;
    private Long farmerId;
}
