package com.agro.backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.dtos.ProviderRequestDto;
import com.agro.backend.dtos.ProviderResponseDto;
import com.agro.backend.entities.AgroServiceProvider;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.exceptions.ResourceNotFoundException;
import com.agro.backend.repositories.AgroServiceProviderRepository;
import com.agro.backend.responses.CreationResponseDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroServiceProviderServiceImpl implements AgroServiceProviderService {

	private final ModelMapper modelmapper;
	private final AgroServiceProviderRepository providerRepository;
	@Override
	public CreationResponseDto createProvider(@Valid ProviderRequestDto providerReqDto) throws ApiPostResponseException {
		providerRepository.findByEmail(providerReqDto.getEmail())
        .ifPresent(p -> {
            throw new ApiPostResponseException("This user already exists!");
        });
		
		AgroServiceProvider entity = modelmapper.map(providerReqDto, AgroServiceProvider.class); // deserialize 
		
		// persist the entity
		AgroServiceProvider saved = providerRepository.save(entity);
		System.out.println(saved);
		
		return new CreationResponseDto("the service provider is registered");
	}
	@Override
	public ProviderResponseDto getProviderById(Long id) {
		AgroServiceProvider provider = providerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No such provider exists "+ id));
		
		return modelmapper.map(provider, ProviderResponseDto.class);
	}
	
}
