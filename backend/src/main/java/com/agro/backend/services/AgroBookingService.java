package com.agro.backend.services;

import java.util.List;

import com.agro.backend.dtos.BookingRequestDto;
import com.agro.backend.dtos.BookingResponseDto;
import com.agro.backend.responses.ApiResponseDto;

public interface AgroBookingService {
	ApiResponseDto createBooking(BookingRequestDto requestDto);
    BookingResponseDto getBookingById(Long id);
    List<BookingResponseDto> getAllBookings();
    ApiResponseDto deleteBooking(Long id);
}
