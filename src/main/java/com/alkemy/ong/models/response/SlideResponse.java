package com.alkemy.ong.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class SlideResponse {

	private Long id;
	
    @NotEmpty(message ="El campo organizationId debe completarlo")
    private Long organizationId;

    @NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    private String imageUrl;

    @NotEmpty(message = "El campo text debe completarlo")
    @NotBlank
    private String text;

    private int slideOrder;
}
