package com.agro.backend.services;

import com.agro.backend.dtos.ProviderRequestDto;
import com.agro.backend.dtos.ProviderResponseDto;
import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.responses.ApiResponseDto;

import jakarta.validation.Valid;

public interface AgroServiceProviderService {

	ApiResponseDto createProvider(@Valid ProviderRequestDto providerReqDto) throws ApiPostResponseException;

	ProviderResponseDto getProviderById(Long id);

	 ApiResponseDto updateProvider(@Valid ProviderRequestDto providerReqDto);

	ApiResponseDto deleteProviderById(Long id);

}
