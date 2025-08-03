package com.agro.backend.controller;

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
import com.agro.backend.services.AgroUserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AgroUserController {

    private final AgroUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AgroUserRequestDTO requestDTO) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(requestDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody AgroUserRequestDTO requestDTO) {
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, requestDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
       
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
  
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }


}
