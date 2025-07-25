package com.agro.backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.AgroUserRequestDTO;
import com.agro.backend.dtos.AgroUserResponseDTO;
import com.agro.backend.repositories.AgroUserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroUserServiceImpl implements AgroUserService {
	private final ModelMapper modelmapper;
	private final AgroUserRepository userRepository;
	@Override
	public AgroUserResponseDTO createUser(AgroUserRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}
  
}
