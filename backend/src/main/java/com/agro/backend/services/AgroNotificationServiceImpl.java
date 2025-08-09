package com.agro.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.AgroNotificationRequestDto;
import com.agro.backend.dtos.AgroNotificationResponseDto;
import com.agro.backend.entities.AgroBooking;
import com.agro.backend.entities.AgroNotification;
import com.agro.backend.entities.AgroUser;
import com.agro.backend.entities.RecipientType;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.repositories.AgroBookingRepository;
import com.agro.backend.repositories.AgroNotificationRepository;
import com.agro.backend.repositories.AgroUserRepository;
import com.agro.backend.responses.ApiResponseDto;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroNotificationServiceImpl implements AgroNotificationService {

    private final ModelMapper modelMapper;
    private final AgroNotificationRepository notificationRepository;
    private final AgroBookingRepository bookingRepository;
    private final AgroUserRepository userRepository;

    @Override
    public ApiResponseDto createNotification(AgroNotificationRequestDto requestDto) {
        AgroBooking booking = bookingRepository.findById(requestDto.getBookingId())
                .orElseThrow(() -> new ApiPostResponseException("Booking not found"));
        AgroUser farmer = userRepository.findById(requestDto.getFarmerId())
                .orElseThrow(() -> new ApiPostResponseException("Farmer not found"));

        AgroNotification notification = new AgroNotification();
        notification.setMessage(requestDto.getMessage());
        notification.setSentAt(LocalDateTime.now());
        notification.setBooking(booking);
        notification.setRecipientId(farmer.getId());
        notification.setRecipientType(RecipientType.USER);

        notificationRepository.save(notification);
        return new ApiResponseDto("Notification created successfully");
    }

    @Override
    public AgroNotificationResponseDto getNotificationById(Long id) {
        AgroNotification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("Notification not found"));
        AgroNotificationResponseDto dto = modelMapper.map(notification, AgroNotificationResponseDto.class);
        dto.setBookingId(notification.getBooking().getId());
        dto.setFarmerId(notification.getRecipientId())
        ;
        return dto;
    }

    @Override
    public List<AgroNotificationResponseDto> getAllNotifications() {
        return notificationRepository.findAll().stream().map(n -> {
            AgroNotificationResponseDto dto = modelMapper.map(n, AgroNotificationResponseDto.class);
            dto.setBookingId(n.getBooking().getId());
            dto.setFarmerId(n.getRecipientId());
            return dto;
        }).toList();
    }

    @Override
    public ApiResponseDto deleteNotification(Long id) {
        AgroNotification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("Notification not found"));
        notificationRepository.delete(notification);
        return new ApiResponseDto("Notification deleted successfully");
    }

    // === New Method Implementation ===
    @Override
    public void createBookingNotification(AgroBooking booking) {
        AgroNotification notification = new AgroNotification();
        notification.setMessage("New booking created for service: " + booking.getService().getName());
        notification.setSentAt(LocalDateTime.now());
        notification.setBooking(booking);
        notification.setRecipientId(booking.getId()); // assuming booking user is the farmer

        notificationRepository.save(notification);
    }
}
