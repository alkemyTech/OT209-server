/*
 Clases Reponse
 */
package com.alkemy.ong.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticateResponse {

	private String email;
	private String password;
	
	
}
