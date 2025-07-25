package com.agro.backend.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.AgroServiceRequestDTO;
import com.agro.backend.dtos.AgroServiceResponseDTO;
import com.agro.backend.entities.AgroService;
import com.agro.backend.entities.AgroServiceProvider;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.repositories.AgroServiceProviderRepository;
import com.agro.backend.repositories.AgroServiceRepository;
import com.agro.backend.responses.CreationResponseDto;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroServiceServiceImpl implements AgroServiceService {

    private final AgroServiceRepository serviceRepository;
    private final AgroServiceProviderRepository providerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CreationResponseDto createService(AgroServiceRequestDTO requestDTO) {
        AgroServiceProvider provider = providerRepository.findById(requestDTO.getProviderId())
                .orElseThrow(() -> new ApiPostResponseException("Provider not found"));

        AgroService service = modelMapper.map(requestDTO, AgroService.class);
        service.setProvider(provider);

        serviceRepository.save(service);

        return new CreationResponseDto("Service created successfully");
    }

    @Override
    public CreationResponseDto updateService(Long id, AgroServiceRequestDTO requestDTO) {
        AgroService existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("Service not found"));

        AgroServiceProvider provider = providerRepository.findById(requestDTO.getProviderId())
                .orElseThrow(() -> new ApiPostResponseException("Provider not found"));

        existingService.setName(requestDTO.getName());
        existingService.setDescription(requestDTO.getDescription());
        existingService.setPrice(requestDTO.getPrice());
        existingService.setProvider(provider);

        serviceRepository.save(existingService);

        return new CreationResponseDto("Service updated successfully");
    }

    @Override
    public CreationResponseDto deleteService(Long id) {
        AgroService service = serviceRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("Service not found"));

        serviceRepository.delete(service);

        return new CreationResponseDto("Service deleted successfully");
    }

    @Override
    public List<AgroServiceResponseDTO> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(s -> {
                    AgroServiceResponseDTO dto = modelMapper.map(s, AgroServiceResponseDTO.class);
                    dto.setProviderId(s.getProvider().getId());
                    return dto;
                }).toList();
    }
}
