package com.alkemy.ong.exception;

public class EmailAlreadyExistException extends RuntimeException {

	private String emailField;

	public EmailAlreadyExistException(String emailField) {
		super(String.format("'%s' already on use.", emailField));
		this.setEmailField(emailField);
	}
	
	public String getEmailField() {
		return this.emailField;
	}
	
	private void setEmailField(String email) {
		this.emailField = email;
	}
}
