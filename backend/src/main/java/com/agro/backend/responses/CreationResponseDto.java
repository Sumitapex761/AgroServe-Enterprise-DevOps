package com.agro.backend.responses;

import lombok.Data;

@Data
public class CreationResponseDto {

	private String msg;
	
	public CreationResponseDto(String msg) {
		this.msg = msg;
	}
}
