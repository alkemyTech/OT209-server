package com.alkemy.ong.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideBasicResponse {

	@ApiModelProperty(name = "imageUrl",
            value = "amazon url for image",
            dataType = "String",
            example = "amazon url for image")
    @NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    private String imageUrl;

	@ApiModelProperty(name = "slideOrder",
            value = "order for slide",
            dataType = "Integer",
            example = "1")
    private int slideOrder;
}
