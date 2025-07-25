package com.agro.backend.services;

import java.util.List;

import com.agro.backend.dtos.AgroUserRequestDTO;
import com.agro.backend.dtos.AgroUserResponseDTO;
import com.agro.backend.responses.CreationResponseDto;

public interface AgroUserService {
    CreationResponseDto createUser(AgroUserRequestDTO requestDTO);
    CreationResponseDto updateUser(Long id, AgroUserRequestDTO requestDTO);
    CreationResponseDto deleteUser(Long id);
    List<AgroUserResponseDTO> getAllUsers();
}
