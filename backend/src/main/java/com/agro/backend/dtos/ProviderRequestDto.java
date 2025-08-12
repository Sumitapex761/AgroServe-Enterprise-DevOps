package com.agro.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderRequestDto {

	 @NotBlank(message = "this field cannot be blank")
	 @Email
	    private String email;
	    
	 @NotBlank(message = "this field cannot be blank")
	    private String name;
	    
	 @NotBlank(message = "this field cannot be blank")
	    private String companyName;
	    
	 @NotBlank(message = "this field cannot be blank")
	    private String contactInfo;
}
