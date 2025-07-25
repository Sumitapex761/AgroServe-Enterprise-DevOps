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
import com.agro.backend.responses.ApiResponseDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AgroServiceProviderServiceImpl implements AgroServiceProviderService {

	private final ModelMapper modelmapper;
	private final AgroServiceProviderRepository providerRepository;
	@Override
	public ApiResponseDto createProvider(@Valid ProviderRequestDto providerReqDto) throws ApiPostResponseException {
		providerRepository.findByEmail(providerReqDto.getEmail())
        .ifPresent(p -> {
            throw new ApiPostResponseException("This user already exists!");
        });
		
		AgroServiceProvider entity = modelmapper.map(providerReqDto, AgroServiceProvider.class); // deserialize 
		
		// persist the entity
		AgroServiceProvider saved = providerRepository.save(entity);
		System.out.println(saved);
		
		return new ApiResponseDto("the service provider is registered");
	}
	
	
	
	@Override
	public ProviderResponseDto getProviderById(Long id) {
		AgroServiceProvider provider = providerRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No such provider exists "+ id));
		
		return modelmapper.map(provider, ProviderResponseDto.class);
	}
	
	
	@Override
	public ApiResponseDto updateProvider(@Valid ProviderRequestDto providerReqDto) {
		AgroServiceProvider provider = providerRepository.findByEmail(providerReqDto.getEmail())
				.orElseThrow(()-> new ResourceNotFoundException("there is no Service Provider by the email"));
		
		provider.setName(providerReqDto.getName());
		provider.setContactInfo(providerReqDto.getContactInfo());
		provider.setCompanyName(providerReqDto.getCompanyName());
		
		return new ApiResponseDto("updated SuccessFully ");
	}
	
	
	@Override
	public ApiResponseDto deleteProviderById(Long id) {
		AgroServiceProvider provider = providerRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No such provider exists "+ id));
		
		System.out.println("id Exists that is to be deleted");
		providerRepository.deleteById(id);
		
		System.out.println(" Provider Deleted ");
		
		return new ApiResponseDto("Deleted with userid: "+ id);
	}
	
	
	
}
