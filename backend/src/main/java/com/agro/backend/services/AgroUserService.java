package com.agro.backend.services;

import com.agro.backend.dtos.AgroUserRequestDTO;
import com.agro.backend.dtos.AgroUserResponseDTO;

public interface AgroUserService {


    AgroUserResponseDTO createUser(AgroUserRequestDTO requestDTO);
//    AgroUserResponseDTO updateUser(Long id, AgroUserRequestDTO requestDTO);
//    AgroUserResponseDTO getUserById(Long id);
//    List<AgroUserResponseDTO> getAllUsers();
//    void deleteUser(Long id);
}
