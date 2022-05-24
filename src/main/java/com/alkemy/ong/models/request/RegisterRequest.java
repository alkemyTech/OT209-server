package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
