package com.agro.backend.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.agro.backend.exceptions.ApiPostResponseException;

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