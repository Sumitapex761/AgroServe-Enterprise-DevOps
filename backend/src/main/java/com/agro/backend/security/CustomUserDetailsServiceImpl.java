package com.agro.backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agro.backend.entities.AgroUser;
import com.agro.backend.repositories.AgroUserRepository;

import lombok.AllArgsConstructor;

@Service //spring bean containing B.L
@Transactional
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	//depcy
	private final AgroUserRepository agroUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		// invoke dao's method
		AgroUser user= agroUserRepository.findByEmail(email)
				.orElseThrow(() ->
				new UsernameNotFoundException("Invalid email !!!!"));
		return user;
	}

}
