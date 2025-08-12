package com.agro.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.dtos.AgroNotificationRequestDto;
import com.agro.backend.dtos.AgroNotificationResponseDto;
import com.agro.backend.responses.ApiResponseDto;
import com.agro.backend.services.AgroNotificationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class AgroNotificationController {

    private final AgroNotificationService notificationService;

//    @PostMapping
//    public ResponseEntity<?> createNotification(@RequestBody @Valid AgroNotificationRequestDto requestDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(requestDto));
//    }
    
    
    // mark the notification as read
    @PostMapping("/{id}/read")
    @PreAuthorize("hasAnyRole('SERVICEPROVIDER', 'FARMER', 'ADMIN')")
    public ResponseEntity<ApiResponseDto> markNotificationRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('SERVICEPROVIDER')")
    public ResponseEntity<?> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteNotification(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.deleteNotification(id));
    }
}
