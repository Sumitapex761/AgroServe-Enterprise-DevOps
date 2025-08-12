package com.agro.backend.controller;

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

import com.agro.backend.dtos.AgroServiceRequestDTO;
import com.agro.backend.services.AgroServiceService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")  // cross origin from React site
@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class AgroServiceController {

    private final AgroServiceService agroService;
    
    /*
     * GET
     * PayLoad -> List of services
     * REsp-> SC 200
     */
    @GetMapping
    @Operation(description = "get all services")
    public ResponseEntity<?> getAllServices() {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.getAllServices());
    }

/*
 * POST -> new SERVICE
 * PayLoad -> Detailed user Service info
 * response -> SC 201 
 */
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")  // role base authorization
	@Operation(description = "Add New Service")
    public ResponseEntity<?> createService(@Valid @RequestBody AgroServiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agroService.createService(requestDTO));
    }
    
    
    @PutMapping("/service/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "update a service ")
    public ResponseEntity<?> updateService(@PathVariable Long id, @Valid @RequestBody AgroServiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.updateService(id, requestDTO));
    }

    @DeleteMapping("/{providerId}/{serviceId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "delete a service")
    public ResponseEntity<?> deleteService(@PathVariable Long providerId, Long serviceId) {
        return ResponseEntity.status(HttpStatus.OK).body(agroService.removeService(providerId, serviceId));
    }
    
    /*
     * GET -> 
     * Pathvariable -> ID
     * REsp-> Service Response DTO
     * ROLE -> permit to all
     */
    @GetMapping("/{id}")
    @Operation(description = "get service By ID")
    public ResponseEntity<?> getServiceById(@PathVariable Long id){
    	return ResponseEntity.status(HttpStatus.OK).body(agroService.getServiceById(id));
    }
  
}
