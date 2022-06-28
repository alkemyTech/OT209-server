package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@ApiModel(value = "SlideRequestDTO", description = "Slide template")
public class SlideRequestDTO {

	@ApiModelProperty(name = "imageUrl",
            value = "amazon url for image",
            dataType = "String",
            example = "amazon url for image")
    private String imageUrl;
	
	@ApiModelProperty(name = "order",
            value = "order for slide",
            dataType = "Integer",
            example = "1")
    private Integer order;
	
	@ApiModelProperty(name = "text",
            value = "description for slide",
            dataType = "String",
            example = "description for slide")
    private String text;

	@ApiModelProperty(name = "organizationId",
            value = "Id for organization",
            dataType = "long",
            example = "1")
    @NotNull(message = "Organization id is mandatory")
    private Long organizationId;

	@ApiModelProperty(name = "encodeImg",
            value = "encoded image on base64",
            dataType = "String",
            example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAQCAIAAABV4/KnAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAENSURBVChTbclPT4JgHMDx3zs2Ix4w68QDgiyzrSYif5oiSRBYq+Es3aJQnGydOnSoDnkxolWvIFrpwbV9Tt8vFNh6hsLSQo1ma0DhLNURIy1l76+uAIpprED/VhIrP5VmVYSVXySjrmMdNks6YnUktJFobfCdNdxEgglFXu9eP55P30/jL2/6KTTHZNkGqT3oTWYVIyzu97zozbh6zmETSKxuVyxRu6waQff2xRo+IdGGvdbQj18bZ/ecGnhh4o4SotwBa/Dgxwm94xXEk4vx3L2Z5XkTJCfy7z7cKD0K5vYodcI0zztAYE3U+rutYKvqYck/OJ7kWAMQp5CcSnIyWiBKh0CzcobipCXEyd/X/Mf17tf+6AAAAABJRU5ErkJggg==")
    @NotBlank(message = "Encode is mandatory")
    private String encodeImg;

    public SlideRequestDTO() {
    }
}
