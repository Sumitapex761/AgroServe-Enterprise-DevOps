package com.agro.backend.exceptions;

public class ApiPostResponseException extends RuntimeException{

	private String msg;
	public ApiPostResponseException(String msg) {
		super(msg);
	}
	

}
