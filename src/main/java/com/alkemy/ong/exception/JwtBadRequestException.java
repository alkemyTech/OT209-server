package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtBadRequestException extends RuntimeException {

	private HttpStatus httpStatus;
	private String message;

	public JwtBadRequestException(HttpStatus badRequest, String message) {
		super(String.format(message));
		this.setHttpStatus(badRequest);
		this.setMessage(message);
	}

}
