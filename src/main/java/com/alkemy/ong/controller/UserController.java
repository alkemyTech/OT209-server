package com.alkemy.ong.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alkemy.ong.auth.config.SwaggerConfig;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.USER_CONTROLLER})
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Update user", notes = "Update an existing user passed by id", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "NO CONTENT - User updated", response = UserResponse.class),
        @ApiResponse(code = 404, message = "NOT FOUND - User not found")})
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@Valid @PathVariable Long id, @RequestBody UserRequest request) {
        return userService.update(id, request);
    }

    @ApiOperation(value = "Delete user", notes = "Delete an existing user passed by id", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "NO CONTENT - User deleted", response = UserResponse.class),
        @ApiResponse(code = 404, message = "NOT FOUND - User not found")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @ApiOperation(value = "List of user", notes = "List of users on database", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK - List of users", response = UserResponse.class),
        @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.")})
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
