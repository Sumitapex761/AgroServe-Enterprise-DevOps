package com.agro.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agro.backend.dtos.BookingRequestDto;
import com.agro.backend.dtos.BookingResponseDto;
import com.agro.backend.responses.ApiResponseDto;
import com.agro.backend.services.AgroBookingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class AgroBookingController {

    private final AgroBookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createBooking(@RequestBody @Valid BookingRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.deleteBooking(id));
    }
}
