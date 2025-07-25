package com.agro.backend.exceptionhandler;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.agro.backend.exceptions.ApiPostResponseException;
import com.agro.backend.exceptions.BadRequestException;
import com.agro.backend.exceptions.ResourceNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiPostResponseException.class)
    public ResponseEntity<?> handleApiResponseException(ApiPostResponseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)  // or HttpStatus.BAD_REQUEST etc.
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server Error: " + ex.getMessage()));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResorceNotFoundException(ResourceNotFoundException ex){
    	return ResponseEntity.status(HttpStatus.NO_CONTENT)
    			.body(new ErrorResponse("Resource Not Found: "+ ex.getMessage()));
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex){
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    			.body(new ErrorResponse("Bad request: "+ex.getMessage()));
    }
    
    
    // Handeling the exceptions of Server Side validations of Request Body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    	Map<String,String> map = ex.getFieldErrors()
    			                   .stream()
    			                   .collect(Collectors.toMap(f-> f.getField(), f->f.getDefaultMessage()));
    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    
    
    // Handling Path variable Exceptions 
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex){
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}