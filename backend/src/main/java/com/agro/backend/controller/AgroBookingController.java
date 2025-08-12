package com.agro.backend.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.dtos.BookingRequestDto;
import com.agro.backend.services.AgroBookingService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AgroBookingController {

    private final AgroBookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('FARMER')")
    @Operation(description="add")
    public ResponseEntity<?> createBooking(@RequestBody @Valid BookingRequestDto requestDto, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(requestDto, principal));
    }
    
    /*
     * POST
     * pathvariable -> ID
     * response -> response DTO 
     */
    
    @PutMapping("/{bookingId}/approve")
    @PreAuthorize("hasRole('SERVICEPROVIDER')")
    public ResponseEntity<?> approveBooking(@PathVariable Long bookingId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.approveBooking(bookingId, principal));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.deleteBooking(id));
    }
}
