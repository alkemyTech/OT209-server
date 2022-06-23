package com.alkemy.ong.models.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Category Request", description = "Format for creating and updating categories")
public class CategoryRequest {
	
	@NotBlank(message = "the name can't be blank")
	@NotNull(message = "the name can't be null")
	@NotEmpty(message = "the name can't be empty")
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "The name has to contain only letters")
	@ApiModelProperty(name = "Name",
    value = "Title of the category",
    dataType = "String",
    notes = "It can't be null",
    example = "Relevante",
    required = true)
	private String name;
	
	@ApiModelProperty(name = "Description",
		    value = "Description of the category",
		    dataType = "String",
		    example = "Lorem Ipsum..."
		    )
	private String description;
	
	@ApiModelProperty(name = "Image",
		    value = "Image of the category",
		    dataType = "String",
		    example = "image0001.jpg"
		    )
	private String image;
}
