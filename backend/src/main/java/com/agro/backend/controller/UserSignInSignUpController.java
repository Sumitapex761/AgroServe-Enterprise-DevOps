package com.agro.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.dtos.SignInDTO;
import com.agro.backend.dtos.SignupReqDTO;
import com.agro.backend.exceptions.ApiException;
import com.agro.backend.responses.AuthResponse;
import com.agro.backend.security.JwtUtils;
import com.agro.backend.services.AgroUserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") 
public class UserSignInSignUpController {

    
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AgroUserService agroUserService;

    /*
     * Desc - sign in
     * URL - http://host:port/users/signin
     * Method - POST
     * Payload - signin dto 
     * Successful Resp - SC 200 , body - msg , JWT
     * Failed - SC 401 , err msg - api resp
     */
    @PostMapping("/signin")
    @Operation(description = "User Sign In")
    public ResponseEntity<?> userSignIn(@RequestBody SignInDTO dto) {
        System.out.println("in sign in " + dto);
        
        Authentication authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        System.out.println("authToken Valid: " + authToken.isAuthenticated()); // false

        Authentication validAuth = authenticationManager.authenticate(authToken);

        System.out.println("after validation: " + validAuth.isAuthenticated()); // true
        System.out.println(validAuth); // user details

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse("Successful login JWT Token: ", jwtUtils.generateJwtToken(validAuth)));
    }

    /*
     * Desc - User registration
     * URL - http://host:port/users/signup
     * Method - POST
     * Payload - user signup request dto
     * Response - SC 201 , user response dto 
     * Error - duplicate email - SC 400 | SC 409 , api response - error msg
     */
     @PostMapping("/signup")
     public ResponseEntity<?> signupUser(@RequestBody @Valid SignupReqDTO dto) throws ApiException {
    	 
         System.out.println("in signup " + dto);
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(agroUserService.signUp(dto));
    }
}
