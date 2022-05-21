/*
 Entidades Request
 */
package com.alkemy.ong.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateRequest {

	@NonNull
	@Email(message = "enter a correct email")
	private String email;
	@NonNull
	@NotEmpty(message = "the password can't be null")
	private String password;
}
