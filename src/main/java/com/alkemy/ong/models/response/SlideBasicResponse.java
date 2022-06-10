package com.alkemy.ong.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideBasicResponse {
	
	@NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    private String imageUrl;

    private int slideOrder;
}
