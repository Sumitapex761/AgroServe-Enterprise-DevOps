package com.agro.backend.exceptions;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ApiPostResponseException extends RuntimeException{

	private String msg;
	public ApiPostResponseException(String msg) {
		super(msg);
	}
	

}
