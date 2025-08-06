package com.agro.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AgroUserController {

    private final AgroUserService userService;

   /*
    * PUT 
    * payLoad -> AgroUserRequestDTO
    * PathVAriable -> LongId
    * RESP -> SC OK
    */
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('FARMER')")
    @Operation(description = "Update User Details")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody AgroUserRequestDTO requestDTO) {
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, requestDTO));
    }
    
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('FARMER')")
    @Operation(description = "delete a User")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "get all users")
    public ResponseEntity<?> getAllUsers() {
  
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }


}
