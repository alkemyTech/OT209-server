package com.alkemy.ong.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SlideResponse", description = "Slide response template")
public class SlideResponse {
	
	@ApiModelProperty(name = "organizationId",
            value = "Id for organization",
            dataType = "long",
            example = "1")
    @NotEmpty(message = "El campo organizationId debe completarlo")
    private Long organizationId;

	@ApiModelProperty(name = "imageUrl",
            value = "amazon url for image",
            dataType = "String",
            example = "amazon url for image")
    @NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    private String imageUrl;

    @ApiModelProperty(name = "text",
            value = "description for slide",
            dataType = "String",
            example = "description for slide")
    @NotEmpty(message = "El campo text debe completarlo")
    @NotBlank
    private String text;

    @ApiModelProperty(name = "order",
            value = "order for slide",
            dataType = "Integer",
            example = "1")
    private int slideOrder;
}
