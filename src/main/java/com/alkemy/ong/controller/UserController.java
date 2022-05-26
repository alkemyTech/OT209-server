/*
Controladores
 */
package com.alkemy.ong.controller;

import javax.validation.Valid;

import com.alkemy.ong.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
import com.alkemy.ong.service.impl.UserServiceImpl;

import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest) throws IOException {
		log.info("enviando Imagen de bienvenida");
		userService.send();
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticateResponse>  signIn(@RequestBody AuthenticateRequest authRequest) throws Exception {
		return ResponseEntity.ok(userService.login(authRequest.getEmail(),authRequest.getPassword()));
		
	}
}
