/*
Controladores
 */
package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.models.request.AuthenticateRequest;
import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticateResponse>  signIn(@RequestBody AuthenticateRequest authRequest) {
		return ResponseEntity.ok(userService.login(authRequest.getEmail(),authRequest.getPassword()));
		
	}
}
