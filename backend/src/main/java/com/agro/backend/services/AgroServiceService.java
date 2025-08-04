package com.agro.backend.services;

import com.agro.backend.dtos.AgroServiceRequestDTO;
import com.agro.backend.dtos.AgroServiceResponseDTO;
import com.agro.backend.responses.ApiResponseDto;

public interface AgroServiceService {
    ApiResponseDto createService(AgroServiceRequestDTO requestDTO);
    ApiResponseDto updateService(Long id, AgroServiceRequestDTO requestDTO);
    ApiResponseDto removeService(Long providerId, Long serviceId);
    AgroServiceResponseDTO getServiceById(Long id);
   
}
