package com.agro.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.services.AgroServicesService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class AgroServicesController {
    private final AgroServicesService service ;
}
