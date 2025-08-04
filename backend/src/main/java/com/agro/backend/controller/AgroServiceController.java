package com.agro.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.dtos.AgroServiceRequestDTO;
import com.agro.backend.services.AgroServiceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")  // cross origin from React site
@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class AgroServiceController {

    private final AgroServiceService agroService;
    
    @GetMapping
    public ResponseEntity<?> getAllServices() {
        return ResponseEntity.ok(agroService.getAllServices());
    }


    @PostMapping
    public ResponseEntity<?> createService(@Valid @RequestBody AgroServiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agroService.createService(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @Valid @RequestBody AgroServiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.updateService(id, requestDTO));
    }

    @DeleteMapping("/{providerId}/{serviceId}")
    public ResponseEntity<?> deleteService(@PathVariable Long providerId, Long serviceId) {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.removeService(providerId, serviceId));
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id){
    	return ResponseEntity.status(HttpStatus.OK).body(agroService.getServiceById(id));
    }
  
}
