package com.agro.backend.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.AgroUserRequestDTO;
import com.agro.backend.dtos.AgroUserResponseDTO;
import com.agro.backend.dtos.SignupReqDTO;
import com.agro.backend.entities.AgroUser;
import com.agro.backend.exceptions.ApiException;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.repositories.AgroUserRepository;
import com.agro.backend.responses.ApiResponseDto;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroUserServiceImpl implements AgroUserService {

    private final ModelMapper modelMapper;
    private final AgroUserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public ApiResponseDto createUser(AgroUserRequestDTO requestDTO) {
        // Check confirm password
        if (!requestDTO.getPassword().equals(requestDTO.getConfirmPassword())) {
            throw new ApiPostResponseException("Password and Confirm Password do not match");
        }

        // DTO -> Entity
        AgroUser user = modelMapper.map(requestDTO, AgroUser.class);

        // Save to DB
        userRepository.save(user);

        // Return success message
        return new ApiResponseDto("User created successfully");
    }
    
    @Override
    public ApiResponseDto updateUser(Long id, AgroUserRequestDTO requestDTO) {
        // Find user by ID
        AgroUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("User not found with id: " + id));

        // Password confirmation check
        if (!requestDTO.getPassword().equals(requestDTO.getConfirmPassword())) {
            throw new ApiPostResponseException("Password and Confirm Password do not match");
        }

        // Map changes from DTO to entity
        modelMapper.map(requestDTO, existingUser);

        // Save updated user
        userRepository.save(existingUser);

        return new ApiResponseDto("User updated successfully");
    }
    
    @Override
    public ApiResponseDto deleteUser(Long id) {
        // Check if user exists
        AgroUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ApiPostResponseException("User not found with id: " + id));

        // Delete user
        userRepository.delete(existingUser);

        return new ApiResponseDto("User deleted successfully");
    }
    
    @Override
    public List<AgroUserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, AgroUserResponseDTO.class))
                .toList();
    }

	@Override
	public AgroUserResponseDTO signUp(SignupReqDTO dto) throws ApiException {
		
		// 1. check for dup email
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new ApiException("this Email already exists");
		}
		// new email 
		// dto -> entity
		AgroUser entity = modelMapper.map(dto, AgroUser.class);
		// encrypt password
		entity.setPassword(encoder.encode(entity.getPassword()));
		//4. save the entity n map persistent entity -> resp dto
		return modelMapper.map(userRepository.save(entity), AgroUserResponseDTO.class);
		
		
	}



}
