package com.agro.backend.services;

import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.BookingRequestDto;
import com.agro.backend.dtos.BookingResponseDto;
import com.agro.backend.entities.AgroBooking;
import com.agro.backend.entities.AgroNotification;
import com.agro.backend.entities.AgroService;
import com.agro.backend.entities.AgroUser;
import com.agro.backend.entities.RecipientType;
import com.agro.backend.entities.Status;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.repositories.AgroBookingRepository;
import com.agro.backend.repositories.AgroNotificationRepository;
import com.agro.backend.repositories.AgroServiceRepository;
import com.agro.backend.repositories.AgroUserRepository;
import com.agro.backend.responses.ApiResponseDto;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroBookingServiceImpl implements AgroBookingService {

    private final AgroBookingRepository bookingRepository;
    private final AgroUserRepository userRepository;
    private final AgroServiceRepository serviceRepository;
    private final ModelMapper modelMapper;
    private final AgroNotificationService notificationService; // <-- Added
    private final AgroNotificationRepository notificationRepository;
    private final EmailService emailService;
    
    
    @Override
    public BookingResponseDto createBooking(BookingRequestDto requestDto , Principal principal) {
    	//  Get logged-in user from token
        AgroUser user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ApiPostResponseException("User not found"));
        
        //Find service
        AgroService service = serviceRepository.findById(requestDto.getServiceId())
                .orElseThrow(() -> new ApiPostResponseException("Service not found"));

        AgroBooking booking = new AgroBooking();
        booking.setStatus(Status.PENDING);
        booking.setPreferredDate(requestDto.getPreferredDate());
        booking.setNotes(requestDto.getNotes());
        booking.setUser(user);
        booking.setService(service);

        bookingRepository.save(booking);      //-> Booking -> save Booking Status  as PENDING

        // Trigger Notification
        // Save notification for provider
        
        AgroNotification notification = new AgroNotification();
        notification.setBooking(booking);
        notification.setMessage("New booking request from " + user.getName());
        notification.setRecipientId(service.getProvider().getId());
        notification.setRecipientType(RecipientType.PROVIDER);               
        notificationRepository.save(notification); // Save Notification 
        
        
        // Send email to provider asynchronously       // -> notify by email to provider
        String emailContent = """
            <h2>New Booking Request</h2>
            <p>You have received a new booking for service: <strong>%s</strong></p>
            <p>Farmer: %s</p>
            <p>Preferred Date: %s</p>
            """.formatted(service.getName(), user.getName(), requestDto.getPreferredDate());
        emailService.sendBookingNotification(service.getProvider().getEmail(), "New Booking Request", emailContent);
        

        // Map entity to DTO
        BookingResponseDto response = modelMapper.map(booking, BookingResponseDto.class);
        response.setServiceName(service.getName());
        response.setUserName(user.getName());

        return response;
    }

    @Override
    public BookingResponseDto getBookingById(Long id) {
        AgroBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("Booking not found"));
        BookingResponseDto dto = modelMapper.map(booking, BookingResponseDto.class);
        dto.setServiceName(booking.getService().getName());
	    dto.setUserName(booking.getUser().getName());
        return dto;
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(b -> {
            BookingResponseDto dto = modelMapper.map(b, BookingResponseDto.class);
            dto.setServiceName(b.getService().getName());
    	    dto.setUserName(b.getUser().getName());
            return dto;
        }).toList();
    }

    @Override
    public ApiResponseDto deleteBooking(Long id) {
        AgroBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("Booking not found"));
        bookingRepository.delete(booking);
        return new ApiResponseDto("Booking deleted successfully");
    }

	@Override
	public BookingResponseDto approveBooking(Long bookingId, Principal principal) {
	    AgroUser provider = userRepository.findByEmail(principal.getName())
	            .orElseThrow(() -> new ApiPostResponseException("Provider not found"));

	    AgroBooking booking = bookingRepository.findById(bookingId)
	            .orElseThrow(() -> new ApiPostResponseException("Booking not found"));

	    // Check that the provider owns the service for this booking
	    if (!booking.getService().getProvider().getId().equals(provider.getId())) {
	        throw new ApiPostResponseException("You are not authorized to approve this booking");
	    }

	    // Update booking status
	    booking.setStatus(Status.CONFIRMED);
	    bookingRepository.save(booking);

	    // Find existing notification
	    AgroNotification notification = notificationRepository.findByBookingId(bookingId)
	        .orElseGet(() -> new AgroNotification()); // create if not found
	    notification.setBooking(booking);
	    notification.setMessage("Your booking for service '" + booking.getService().getName() + "' has been approved.");
	    notification.setRecipientId(booking.getUser().getId());
	    notification.setRecipientType(RecipientType.USER);
	    notificationRepository.save(notification);

	    // Send email to farmer
	    String emailContent = """
	        <h2>Booking Confirmed</h2>
	        <p>Your booking for <strong>%s</strong> has been confirmed by the provider.</p>
	        <p>Preferred Date: %s</p>
	        """.formatted(booking.getService().getName(), booking.getPreferredDate());
	    emailService.sendBookingNotification(
	            booking.getUser().getEmail(),
	            "Booking Approved",
	            emailContent
	    );

	    // Map entity to DTO using ModelMapper
	    BookingResponseDto response = modelMapper.map(booking, BookingResponseDto.class);
	    response.setServiceName(booking.getService().getName());
	    response.setUserName(booking.getUser().getName());

	    return response;
	}

//	
}
