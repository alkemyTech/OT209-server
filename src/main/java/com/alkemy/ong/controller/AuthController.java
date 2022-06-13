package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.AuthenticateRequest;
import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.service.AuthService;
import javax.validation.Valid;

import com.alkemy.ong.service.EmailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Api(value = "Authentication controller", description = "This API has a Authenticated for users")

public class AuthController {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest) throws IOException {
		emailService.sendTemplateSolosMas(registerRequest.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticateResponse> signIn(@RequestBody AuthenticateRequest authRequest) throws Exception {
		return ResponseEntity.ok(authService.login(authRequest.getEmail(), authRequest.getPassword()));

	}

	@GetMapping("/me")
	public ResponseEntity<UserResponse> getUser(@RequestHeader(name = "Authorization") String token) {
		System.out.println(token);

		return ResponseEntity.status(HttpStatus.OK).body(authService.userAuth(token));
	}

}
