package com.agro.backend.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.BookingRequestDto;
import com.agro.backend.dtos.BookingResponseDto;
import com.agro.backend.entities.AgroBooking;
import com.agro.backend.entities.AgroService;
import com.agro.backend.entities.AgroUser;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.repositories.AgroBookingRepository;
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

    @Override
    public ApiResponseDto createBooking(BookingRequestDto requestDto) {
        AgroUser user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ApiPostResponseException("User not found"));
        AgroService service = serviceRepository.findById(requestDto.getServiceId())
                .orElseThrow(() -> new ApiPostResponseException("Service not found"));

        AgroBooking booking = new AgroBooking();
        booking.setStatus(requestDto.getStatus());
        booking.setUser(user);
        booking.setService(service);

        bookingRepository.save(booking);

        // Trigger Notification
        notificationService.createBookingNotification(booking);

        return new ApiResponseDto("Booking created successfully and notification sent");
    }

    @Override
    public BookingResponseDto getBookingById(Long id) {
        AgroBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("Booking not found"));
        BookingResponseDto dto = modelMapper.map(booking, BookingResponseDto.class);
        dto.setUserId(booking.getUser().getId());
        dto.setServiceId(booking.getService().getId());
        return dto;
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(b -> {
            BookingResponseDto dto = modelMapper.map(b, BookingResponseDto.class);
            dto.setUserId(b.getUser().getId());
            dto.setServiceId(b.getService().getId());
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
}
