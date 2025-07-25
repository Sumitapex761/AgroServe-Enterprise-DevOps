package com.agro.backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.AgroServiceRequestDTO;
import com.agro.backend.entities.AgroService;
import com.agro.backend.entities.AgroServiceProvider;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.exceptions.ResourceNotFoundException;
import com.agro.backend.repositories.AgroServiceProviderRepository;
import com.agro.backend.repositories.AgroServiceRepository;
import com.agro.backend.responses.ApiResponseDto;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroServiceServiceImpl implements AgroServiceService {

    private final AgroServiceRepository serviceRepository;
    private final AgroServiceProviderRepository providerRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponseDto createService(AgroServiceRequestDTO requestDTO) {
        AgroServiceProvider provider = providerRepository.findById(requestDTO.getProviderId())
                .orElseThrow(() -> new ApiPostResponseException("Provider not found"));

        AgroService service = modelMapper.map(requestDTO, AgroService.class);
        provider.addService(service);

        serviceRepository.save(service);

        return new ApiResponseDto("Service created successfully");
    }

    @Override
    public ApiResponseDto updateService(Long id, AgroServiceRequestDTO requestDTO) {
        AgroService existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        AgroServiceProvider provider = providerRepository.findById(requestDTO.getProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found"));

        existingService.setName(requestDTO.getName());
        existingService.setDescription(requestDTO.getDescription());
        existingService.setPrice(requestDTO.getPrice());
        existingService.setProvider(provider);

        serviceRepository.save(existingService);

        return new ApiResponseDto("Service updated successfully");
    }

    @Override
    public ApiResponseDto removeService(Long providerId, Long serviceId) {
    	 AgroServiceProvider provider = providerRepository.findById(providerId)
    	            .orElseThrow(() -> new ResourceNotFoundException("Provider not found"));

    	    AgroService service = serviceRepository.findById(serviceId)
    	            .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

    	    // Remove service from provider
    	    provider.removeService(service);

    	    // No need to explicitly call delete, orphanRemoval will handle it
    	    providerRepository.save(provider); // Ensures Hibernate flushes changes

    	    return new ApiResponseDto("Service removed successfully");
    }

   
}
