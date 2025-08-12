package com.agro.backend.services;

import java.util.List;

import com.agro.backend.dtos.AgroNotificationRequestDto;
import com.agro.backend.dtos.AgroNotificationResponseDto;
import com.agro.backend.entities.AgroBooking;
import com.agro.backend.entities.AgroUser;
import com.agro.backend.responses.ApiResponseDto;

public interface AgroNotificationService {
    ApiResponseDto createNotification(AgroNotificationRequestDto requestDto);
    AgroNotificationResponseDto getNotificationById(Long id);
    List<AgroNotificationResponseDto> getAllNotifications();
    ApiResponseDto deleteNotification(Long id);
    
    public List<AgroNotificationResponseDto> getNotificationsForCurrentUser(AgroUser currentUser) ;

    // NEW METHOD
    void createBookingNotification(AgroBooking booking);
    
    ApiResponseDto markAsRead(Long notificationId);

}
