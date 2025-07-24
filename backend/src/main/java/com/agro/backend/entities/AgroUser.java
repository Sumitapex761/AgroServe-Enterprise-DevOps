package com.agro.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "AgroUser")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AgroUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	/*
	 * name 
	 * email
	 * phone
	 * password 
	 * confirm password
	 * role ENUM
	 * address string
	 * 
	 * 
	 * 
	 */
	
	@Column(nullable = false)
	private String  name ;
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(nullable = false)
	private String phone;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column
	private String Address;
	
	
}
