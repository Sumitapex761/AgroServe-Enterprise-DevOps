package com.agro.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        AgroUser farmer = userRepository.findById(requestDto.getRecipientId())
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

    	    // Safely set bookingId
    	    if (notification.getBooking() != null) {
    	        dto.setBookingId(notification.getBooking().getId());
    	    }

    	    dto.setRecipientId(notification.getRecipientId());

    	    // Map enum to String to avoid serialization issues
    	    if (notification.getRecipientType() != null) {
    	        dto.setRecipientType(notification.getRecipientType().name());
    	    }

    	    return dto;
    }

    @Override
    public List<AgroNotificationResponseDto> getAllNotifications() {
        List<AgroNotification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AgroNotificationResponseDto convertToDto(AgroNotification notification) {
        AgroNotificationResponseDto dto = new AgroNotificationResponseDto();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setRecipientId(notification.getRecipientId());
        dto.setRecipientType(notification.getRecipientType().name());
        dto.setSentAt(notification.getSentAt());
        if (notification.getBooking() != null) {
            dto.setBookingId(notification.getBooking().getId());
        }
        return dto;
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

	@Override
	public ApiResponseDto markAsRead(Long notificationId) {
		AgroNotification notification = notificationRepository.findById(notificationId)
		        .orElseThrow(() -> new ApiPostResponseException("Notification not found"));

		    notification.setRead(true);  // assuming you have a boolean field isRead in AgroNotification entity
		    notificationRepository.save(notification);

		    return new ApiResponseDto("Notification marked as read");
	}

	@Override
	public List<AgroNotificationResponseDto> getNotificationsForCurrentUser(AgroUser currentUser) {
		 Long userId = currentUser.getId();

		    // Map Role to RecipientType enum (adjust enum names accordingly)
		    RecipientType recipientType;
		    switch (currentUser.getRole()) {
		        case ROLE_SERVICEPROVIDER:
		            recipientType = RecipientType.PROVIDER;
		            break;
		        case ROLE_FARMER:
		            recipientType = RecipientType.USER;
		            break;
		        case ROLE_ADMIN:
		            // If Admin can have notifications too, define logic or skip
		            recipientType = null; // or your admin enum
		            break;
		        default:
		            throw new IllegalArgumentException("Unknown role: " + currentUser.getRole());
		    }

		    // Defensive: if no valid recipientType, return empty list
		    if (recipientType == null) return List.of();

		    // Query notifications by recipientId and recipientType
		    List<AgroNotification> notifications = notificationRepository.findByRecipientIdAndRecipientType(userId, recipientType);

		    // Convert entities to DTOs before returning
		    return notifications.stream()
		            .map(this::convertToDto)
		            .collect(Collectors.toList());
	}
	
//	private AgroNotificationResponseDto convertToDto(AgroNotification entity) {
//	    AgroNotificationResponseDto dto = new AgroNotificationResponseDto();
//	    dto.setId(entity.getId());
//	    dto.setMessage(entity.getMessage());
//	    dto.setSentAt(entity.getSentAt());
//	    if (entity.getBooking() != null) {
//	        dto.setBookingId(entity.getBooking().getId());
//	    }
//	    // add any other fields you want to expose
//	    return dto;
//	}
}
