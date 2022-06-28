package com.alkemy.ong.service;

import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.models.response.UserResponse;

public interface AuthService {

	public RegisterResponse register(RegisterRequest user);

	public AuthenticateResponse login(String email, String password) throws Exception;

	public UserResponse userAuth(String token);


}
