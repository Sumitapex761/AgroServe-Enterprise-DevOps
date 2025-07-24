package com.agro.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.services.AgroServiceProviderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/provider")
@AllArgsConstructor
public class AgroServiceProviderController {

	private final AgroServiceProviderService providerService;
	
}
