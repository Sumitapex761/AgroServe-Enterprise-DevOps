package com.agro.backend.services;

import java.util.List;

import com.agro.backend.dtos.AgroUserRequestDTO;
import com.agro.backend.dtos.AgroUserResponseDTO;
import com.agro.backend.responses.ApiResponseDto;

public interface AgroUserService {
    ApiResponseDto createUser(AgroUserRequestDTO requestDTO);
    ApiResponseDto updateUser(Long id, AgroUserRequestDTO requestDTO);
    ApiResponseDto deleteUser(Long id);
    List<AgroUserResponseDTO> getAllUsers();
}
