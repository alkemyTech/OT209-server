/*
 interface service
 */
package com.alkemy.ong.service;

import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;

public interface UserService {

	public RegisterResponse register(RegisterRequest user);

	public AuthenticateResponse login(String email, String password);
}
