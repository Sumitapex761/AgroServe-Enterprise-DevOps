package com.agro.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.dtos.AgroUserRequestDTO;
import com.agro.backend.dtos.AgroUserResponseDTO;
import com.agro.backend.responses.CreationResponseDto;
import com.agro.backend.services.AgroUserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AgroUserController {

    private final AgroUserService userService;

    @PostMapping
    public ResponseEntity<CreationResponseDto> createUser(@Valid @RequestBody AgroUserRequestDTO requestDTO) {
        CreationResponseDto response = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CreationResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody AgroUserRequestDTO requestDTO) {
        CreationResponseDto response = userService.updateUser(id, requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<CreationResponseDto> deleteUser(@PathVariable Long id) {
        CreationResponseDto response = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AgroUserResponseDTO>> getAllUsers() {
        List<AgroUserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


}
