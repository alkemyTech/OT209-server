
package com.alkemy.ong.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@ApiModel(value = "Testimonial Request", description = "Format for creating and updating Testimonial")
public class TestimonialRequest {
    	@NotBlank
	@NotNull(message = "the name can't be null")
	@NotEmpty(message = "the name can't be empty")
	@ApiModelProperty(name = "Name",
					  value = "Title of the testimonial",
			   		  dataType = "String",
				      notes = "It can't be null",
				      example = "Gracias",
				      required = true)
	private String name;

	@ApiModelProperty(name = "Image",
			value = "Emblematic image of the news",
			dataType = "String",
			notes = "null permited",
			example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAQCAIAAABV4/KnAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAENSURBVChTbclPT4JgHMDx3zs2Ix4w68QDgiyzrSYif5oiSRBYq+Es3aJQnGydOnSoDnkxolWvIFrpwbV9Tt8vFNh6hsLSQo1ma0DhLNURIy1l76+uAIpprED/VhIrP5VmVYSVXySjrmMdNks6YnUktJFobfCdNdxEgglFXu9eP55P30/jL2/6KTTHZNkGqT3oTWYVIyzu97zozbh6zmETSKxuVyxRu6waQff2xRo+IdGGvdbQj18bZ/ecGnhh4o4SotwBa/Dgxwm94xXEk4vx3L2Z5XkTJCfy7z7cKD0K5vYodcI0zztAYE3U+rutYKvqYck/OJ7kWAMQp5CcSnIyWiBKh0CzcobipCXEyd/X/Mf17tf+6AAAAABJRU5ErkJggg==",
			required = true)
	private String image;

	@NotBlank
	@NotNull(message = "the content can't be null")
	@NotEmpty(message = "the content can't be empty")
	@ApiModelProperty(name = "Content",
			value = "Content of the Testimonial",
			dataType = "String",
			notes = "It can't be null",
			example = "Gracias ONG somos más por ayudarme",
			required = true)
	private String content;
}
