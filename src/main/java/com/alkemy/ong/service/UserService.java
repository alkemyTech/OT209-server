/*
 interface service
 */
package com.alkemy.ong.service;

import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;

import java.io.IOException;

public interface UserService {

	public RegisterResponse register(RegisterRequest user);

	public AuthenticateResponse login(String email, String password) throws Exception;
	void send() throws IOException;
}
