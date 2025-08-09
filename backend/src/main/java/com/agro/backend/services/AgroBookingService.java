package com.agro.backend.services;

import java.security.Principal;
import java.util.List;

import com.agro.backend.dtos.BookingRequestDto;
import com.agro.backend.dtos.BookingResponseDto;
import com.agro.backend.responses.ApiResponseDto;

public interface AgroBookingService {
	BookingResponseDto createBooking(BookingRequestDto requestDto, Principal principal);
    BookingResponseDto getBookingById(Long id);
    List<BookingResponseDto> getAllBookings();
    ApiResponseDto deleteBooking(Long id);
	BookingResponseDto approveBooking(Long bookingId, Principal principal);
}
