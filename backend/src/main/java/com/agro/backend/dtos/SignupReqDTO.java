package com.agro.backend.dtos;

import com.agro.backend.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SignupReqDTO {
/*
 * name ,email, phone, password , confirmpassword, role address 
 */
	
	 @NotBlank(message = "Name is mandatory")
	    private String name;

	    @Email(message = "Email should be valid")
	    @NotBlank(message = "Email is mandatory")
	    private String email;

	    @NotBlank(message = "Password is mandatory")
	    @Size(min = 6, message = "Password must be at least 6 characters long")
	    private String password;

	    @NotBlank(message = "Confirm password is required")
	    private String confirmPassword;

	    @Pattern(regexp = "^\\d{10}$", message = "Phone must be 10 digits")
	    @NotBlank(message = "Phone is mandatory")
	    private String phone;

	    @NotNull(message = "Role is mandatory")
	    private Role role;

	    private String address;
	
}
