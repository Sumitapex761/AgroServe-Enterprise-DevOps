package com.agro.backend.responses;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiResponseDto {

	private String msg;
	
	public ApiResponseDto(String msg) {
		this.msg = msg;
	}
}
