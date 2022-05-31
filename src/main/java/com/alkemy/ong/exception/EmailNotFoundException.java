package com.alkemy.ong.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailNotFoundException extends RuntimeException {

	private String emailField;

	public EmailNotFoundException(String emailField) {
		super(String.format("'%s' not found.", emailField));
		this.setEmailField(emailField);
	}
	
}