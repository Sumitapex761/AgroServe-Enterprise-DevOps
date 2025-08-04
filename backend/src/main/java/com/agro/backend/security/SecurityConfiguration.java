package com.agro.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity  // mandatory annotation for enabling security
@EnableMethodSecurity  // mandatory annotation for enabling method level security
@AllArgsConstructor
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		// disable csrf we are using REST (Stateless, no cookies, no sessions)
		http.csrf((csrf)->csrf.disable())
		.authorizeHttpRequests(
				request->request.requestMatchers("/**")
				.permitAll()
				)
		.httpBasic(Customizer.withDefaults())
		
		// disable session , cookies  as HTTP is stateless
	    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
	

}
