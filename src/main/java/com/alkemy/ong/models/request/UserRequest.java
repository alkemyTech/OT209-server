package com.alkemy.ong.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(value = "User request", description = "Request params for user to be updated")
public class UserRequest {
	
    @ApiModelProperty(name = "id", 
			value = "User id", 
			dataType = "Long", 
			example = "1")
	private Long id;
	
	@ApiModelProperty(name = "firstName", 
			value = "User first name", 
			dataType = "String", 
			example = "Juan")
	@NotBlank(message = "the name can't  be blank")   
	private String firstName;
	
	@ApiModelProperty(name = "lastName", 
			value = "User last name", 
			dataType = "String", 
			example = "Bodoque")
	@NotBlank(message = "the lastName can't  be blank") 
	private String lastName;
	
	@ApiModelProperty(name = "email", 
			value = "User email", 
			dataType = "String", 
			example = "email@server.domain")
	@Email(message = "enter a correct email")  
	private String email;
	
	@ApiModelProperty(name = "password", 
			value = "User password", 
			dataType = "String", 
			example = "pswd_example")
	@NotEmpty(message = "the password can't be empty")
	private String password;
	
	@ApiModelProperty(name = "photo", 
			value = "User photo", 
			dataType = "String", 
			example = "pathToPhoto.jpg")
	private String photo;
}