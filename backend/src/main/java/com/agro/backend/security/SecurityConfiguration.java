package com.agro.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity  // mandatory annotation for enabling security
@EnableMethodSecurity  // mandatory annotation for enabling method level security
@AllArgsConstructor
public class SecurityConfiguration {
	
	private final PasswordEncoder passwordEncoder;
	private final CustomJwtFilter customJwtFilter;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		// 1. Disable CSRF protection
				http.csrf(csrf -> csrf.disable());
				// 2. Authenticate any request
				http.authorizeHttpRequests(request ->
				// 3.permit all - swagger , view all restaurants , user signin , sign up....
				request.requestMatchers("/swagger-ui/**", "/v**/api-docs/**"
						, "/users/signin", "/users/signup").permitAll()
				//  /error - public
					.requestMatchers("/error").permitAll()
				//  Front end React In Flight requesta - allo
				.requestMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated());
				
		
				// 5. set session creation policy - stateless
				http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
				// 6. add custom JWT filter before -UserNamePasswordAuthFilter
				http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
				// 7. Customize error code of SC 401 | SC 403, in case of authentication failure
				http.exceptionHandling(ex -> 
				ex.authenticationEntryPoint(jwtAuthEntryPoint));
				return http.build();
				
	}
	
	// configure a Spring bean to return Spring security Authentication Manager
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration mgr)throws Exception {
		return mgr.getAuthenticationManager();
	}
	
	

}
