package com.alkemy.ong.controller;

import com.alkemy.ong.auth.config.SwaggerConfig;
import com.alkemy.ong.auth.utility.AuthenticationErrorEnum;
import com.alkemy.ong.exception.EmailNotFoundException;
import com.alkemy.ong.models.request.AuthenticateRequest;
import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.service.AuthService;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Api(tags = {SwaggerConfig.AUTH_CONTROLLER})
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "New user creation", notes = "Register a new user", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED - User created", response = AuthenticateResponse.class),
        @ApiResponse(code = 500, message = "EMAIL ALREADY EXIST - Use another email", response = EmailNotFoundException.class)})
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }

    @ApiOperation(value = "Login with email and password", notes = "Login on the API", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "OK - User authenticated", response = AuthenticateResponse.class),
        @ApiResponse(code = 401, message = "UNAUTHORIZED - Bad credentials", response = AuthenticateResponse.class)})
    @PostMapping("/login")
    public ResponseEntity<AuthenticateResponse> signIn(@RequestBody AuthenticateRequest authRequest) throws Exception {
        AuthenticateResponse check = authService.login(authRequest.getEmail(), authRequest.getPassword());
        return check.getEmail() != AuthenticationErrorEnum.OK.name() ? ResponseEntity.ok(check) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(check);

    }

    @ApiOperation(value = "Retrieve user data", notes = "Retrieve user data with the token previously generated", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK - User authenticated", response = UserResponse.class),
        @ApiResponse(code = 403, message = "UNAUTHORIZED - Bad credentials")})
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.userAuth(token));
    }

}
