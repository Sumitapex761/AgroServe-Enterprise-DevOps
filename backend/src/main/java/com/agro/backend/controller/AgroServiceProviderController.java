package com.agro.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.dtos.ProviderRequestDto;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.services.AgroServiceProviderService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/provider")
@AllArgsConstructor
@Validated
public class AgroServiceProviderController {

	private final AgroServiceProviderService providerService;
	
	// POST Method
	// DTO No id -> Email(unique);
	@PostMapping("/addprovider")
	public ResponseEntity<?> createProvider(@RequestBody @Valid ProviderRequestDto providerReqDto) throws ApiPostResponseException{
		System.out.println("this is postmapping of create provider");
		return ResponseEntity.status(HttpStatus.CREATED).body(providerService.createProvider(providerReqDto));
	}
	
	// Get a Service Provider 
	// id , email -> Pathvariable (id)
	// response DTO 
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProviderById(@PathVariable Long id){
		System.out.println("in the get By Id ProviderController");
		return ResponseEntity.status(HttpStatus.OK).body(providerService.getProviderById(id));
	}
	
	
	
	
}
