package com.alkemy.ong.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Register request", description = "Request params for a new user to be generated and registered on database")
public class RegisterRequest {

	@ApiModelProperty(name = "firstName",
		value = "User fisr name",
		dataType = "String",
		example = "Mario")
	@NotNull
	@NotEmpty(message = "the name can't be null")
	@NotBlank(message = "the name can't  be blank")
	private String firstName;
	
	@ApiModelProperty(name = "lastName",
		value = "User last name",
		dataType = "String",
		example = "Santos")
	@NotNull
	@NotEmpty(message = "the lastName can't be null")
	@NotBlank(message = "the lastName can't  be blank")
	private String lastName;
	
	@ApiModelProperty(name = "email",
		value = "User email",
		dataType = "String",
		example = "mariosantos@test.com")
	@NonNull
	@Email(message = "enter a correct email")
	private String email;
	
	@ApiModelProperty(name = "password",
		value = "User password",
		dataType = "String",
		example = "mario123")
	@NotNull
	@NotEmpty(message = "the password can't be null")
	private String password;
}