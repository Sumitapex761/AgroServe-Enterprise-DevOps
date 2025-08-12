package com.agro.backend.exceptions;

public class ApiException extends Exception {

	private String messge;
	public ApiException(String message) {
		super(message);
	}
}
