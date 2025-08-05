package com.agro.backend.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.agro.backend.entities.AgroUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	//inject the props in JWT Utils class for creating n validation of JWT
		/*
		 * @Value => injection of a value (<constr-arg name n value : xml tags) arg - Spring
		 * expression Lang - SpEL
		 * // example of value injected as dependency , using SpEL
		 * (Spring Expression Language)
		 */
		@Value("${jwt.secret.key}") //expression-driven or property-driven dependency injection. 	
		private String jwtSecret;

		@Value("${jwt.expiration.time}")
		private int jwtExpirationMs;

		private SecretKey key;//=> represents symmetric key
		
		@PostConstruct    // this method is invoked after the dependency injection
		public void init() {
			log.info(jwtSecret);
			log.info("Key {} Exp Time {}",jwtSecret,jwtExpirationMs);
			/*create secret key instance from  Keys class
			 * Keys - builder of Secret key
			 * Create a Secret Key using HMAC-SHA256 encryption algo.
			 */		
			key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		}
		
		// will be invoked by UserSignIn  controller , upon successful
		// authentication
		
		public String generateJwtToken(Authentication authentication) {
			log.info("generate jwt token " + authentication);// contains verified user details
			AgroUser userPrincipal = 
					(AgroUser) authentication.getPrincipal();
			
			
			// build jwt token
			return Jwts.builder() // JWTs : a Factory class , used to create JWT tokens
					.subject((userPrincipal.getUsername())) // setting subject part of the token
					.issuedAt(new Date())// Sets the JWT Claims iat (issued at) value of current date
					.expiration(new Date((new Date()).getTime() 
							+ jwtExpirationMs))// Sets the JWT Claims exp
																					// (expiration) value.
					// setting a custom claim , to add granted authorities
					.claim("authorities", 
							getAuthoritiesInString(userPrincipal.getAuthorities()))
				
					// setting a custom claim , to add user id (remove it if not required in the
					// project) - future
				
					.signWith(key, Jwts.SIG.HS256) // Signs the constructed JWT using the specified
					// algorithm with the specified key, producing a
					// JWS(Json web signature=signed JWT)

	// Using token signing algo : HMAC using SHA-512
	                .compact();// Actually builds the JWT and serializes it to a compact, URL-safe string

					
		}
		
		// to convert the GrantedAuthority type to String
		private List<String> getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
			return authorities.stream()
					.map(GrantedAuthority::getAuthority) // (authority->authority.getAuthrity)
					.collect(Collectors.toList());
		}

		public Authentication populateAuthenticationTokenFromJWT(String jwt) {
			// validate JWT n retrieve JWT body (claims)
			Claims payloadClaims = validateJwtToken(jwt);
			// get user name from the claims
			String email = getUserNameFromJwtToken(payloadClaims);
			// get granted authorities as a custom claim
			List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
			// add user name/email , null:password granted authorities in Authentication object
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, null, authorities);
			System.out.println("is authenticated " + token.isAuthenticated());// true
			
			return token;
		}
		
		
		private List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
			List<String> grantedAuthoritiesNamesFromJwt = (List<String>)claims.get("authorities");  
			
		// from List of String convert them to GrantedAthourities using SimpleGrantedAthourity 
			return grantedAuthoritiesNamesFromJwt.stream()  // Stream of Strings
					.map(SimpleGrantedAuthority::new)  // authority-> new SimpleGrantedAuthority(authority)  // invoking the parametarized CTOR
					.collect(Collectors.toList());
		}

		// this method will be invoked by our custom JWT filter
		private String getUserNameFromJwtToken(Claims claims) {
			return claims.getSubject();
		}

		// this method will be invoked by our custom JWT filter
		private Claims validateJwtToken(String jwtToken) {
			Claims claims = Jwts.parser()
					.verifyWith(key)   //// sets the SAME secret key for JWT signature verification
					.build()
					
					// rets the JWT parser set with the Key
					.parseSignedClaims(jwtToken) // rets JWT with Claims added in the body
					.getPayload();// => JWT valid , rets the Claims(payload)
			/*
			 * parseClaimsJws - throws:UnsupportedJwtException -if the JWT body | payload
			 * does not represent any Claims JWSMalformedJwtException - if the JWT body |
			 * payload is not a valid JWSSignatureException - if the JWT signature
			 * validation fails ExpiredJwtException - if the specified JWT is expired
			 * IllegalArgumentException - if the JWT claims body | payload is null or empty
			 * or only whitespace
			 */
			return claims;
		}
}
