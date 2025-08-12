package com.agro.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResponseDto {
	
	 private Long id;
	    
	    private String email;
	    

	    private String name;
	    

	    private String companyName;
	    

	    private String contactInfo;
}
