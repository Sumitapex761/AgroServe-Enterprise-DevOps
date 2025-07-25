package com.agro.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.dtos.AgroUserRequestDTO;
import com.agro.backend.dtos.AgroUserResponseDTO;
import com.agro.backend.services.AgroUserService;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AgroUserController {
    private final AgroUserService userService;
    
    @PostMapping
    public ResponseEntity<AgroUserResponseDTO> createUser(@Valid @RequestBody AgroUserRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.createUser(requestDTO));
    }
}
