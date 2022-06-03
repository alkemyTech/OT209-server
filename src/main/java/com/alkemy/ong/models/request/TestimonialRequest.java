
package com.alkemy.ong.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter @Builder
public class TestimonialRequest {
    	@NotBlank
	@NotNull(message = "the name can't be null")
	@NotEmpty(message = "the name can't be empty")	
	private String name;
	private String image;	
	@NotBlank
	@NotNull(message = "the content can't be null")
	@NotEmpty(message = "the content can't be empty")
	private String content;
}
