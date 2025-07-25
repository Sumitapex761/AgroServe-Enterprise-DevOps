package com.agro.backend.services;

import java.util.List;

import com.agro.backend.dtos.AgroServiceRequestDTO;
import com.agro.backend.dtos.AgroServiceResponseDTO;
import com.agro.backend.responses.CreationResponseDto;

public interface AgroServiceService {
    CreationResponseDto createService(AgroServiceRequestDTO requestDTO);
    CreationResponseDto updateService(Long id, AgroServiceRequestDTO requestDTO);
    CreationResponseDto deleteService(Long id);
    List<AgroServiceResponseDTO> getAllServices();
}
