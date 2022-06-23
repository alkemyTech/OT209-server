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
    private long organizationId;

	@ApiModelProperty(name = "encodeImg",
            value = "encoded image on base64",
            dataType = "String",
            example = "encoded image must be on base64!")
    @NotBlank(message = "Encode is mandatory")
    private String encodeImg;

    public SlideRequestDTO() {
    }
}
