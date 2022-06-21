package com.alkemy.ong.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Autheticate request", description = "Request for the user to be logged in on our API")
public class AuthenticateRequest {
	@ApiModelProperty(name = "email",
        value = "User email",
        dataType = "String",
        example = "admin1@test.com")
	@NonNull
	@Email(message = "enter a correct email")
	private String email;
	@ApiModelProperty(name = "password",
	        value = "User password",
	        dataType = "String",
	        example = "12345678")
	@NonNull
	@NotEmpty(message = "the password can't be null")
	private String password;
}