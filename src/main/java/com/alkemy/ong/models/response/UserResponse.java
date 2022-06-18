package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel(value = "User response", description = "Response for user/s requested")
public class UserResponse {
	
	@ApiModelProperty(name = "id", 
			value = "User id", 
			dataType = "Long", 
			example = "1")
	private Long id;
	
	@ApiModelProperty(name = "firstName", 
			value = "User first name", 
			dataType = "String", 
			example = "Juan")
	private String firstName;
	
	@ApiModelProperty(name = "lastName", 
			value = "User last name", 
			dataType = "String", 
			example = "Bodoque")
	private String lastName;
	
	@ApiModelProperty(name = "email", 
			value = "User email", 
			dataType = "String", 
			example = "email@server.domain")
	private String email;
	
	@ApiModelProperty(name = "photo", 
			value = "User photo", 
			dataType = "String", 
			example = "pathToPhoto.jpg")
	private String photo;
}