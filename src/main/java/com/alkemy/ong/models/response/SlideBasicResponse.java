package com.alkemy.ong.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class SlideBasicResponse {
	
	@NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    private String imageUrl;

    private int slideOrder;
}
