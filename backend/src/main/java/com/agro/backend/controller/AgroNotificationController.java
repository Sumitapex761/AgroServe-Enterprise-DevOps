package com.agro.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class AgroNotificationController {

    private final AgroNotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody @Valid AgroNotificationRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteNotification(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.deleteNotification(id));
    }
}
