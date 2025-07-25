package com.agro.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agro.backend.dtos.AgroServiceRequestDTO;
import com.agro.backend.dtos.AgroServiceResponseDTO;
import com.agro.backend.responses.CreationResponseDto;
import com.agro.backend.services.AgroServiceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class AgroServiceController {

    private final AgroServiceService agroService;

    @PostMapping
    public ResponseEntity<CreationResponseDto> createService(@Valid @RequestBody AgroServiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agroService.createService(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreationResponseDto> updateService(@PathVariable Long id, @Valid @RequestBody AgroServiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.updateService(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CreationResponseDto> deleteService(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.deleteService(id));
    }

    @GetMapping
    public ResponseEntity<List<AgroServiceResponseDTO>> getAllServices() {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.getAllServices());
    }
}
