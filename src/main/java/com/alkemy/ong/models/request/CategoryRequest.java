package com.alkemy.ong.models.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
	@NotBlank
	@NotNull(message = "the name can't be null")
	@NotEmpty(message = "the name can't be empty")
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "The name has to contain only letters")
	private String name;
	
	private String description;
	
	private String image;
}
