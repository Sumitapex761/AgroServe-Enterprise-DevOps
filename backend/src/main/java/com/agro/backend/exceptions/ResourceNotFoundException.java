package com.agro.backend.exceptions;


@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
