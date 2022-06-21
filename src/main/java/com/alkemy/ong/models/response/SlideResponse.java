package com.alkemy.ong.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideResponse {

    @NotEmpty(message = "El campo organizationId debe completarlo")
    private Long organizationId;

    @NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    private String imageUrl;

    @NotEmpty(message = "El campo text debe completarlo")
    @NotBlank
    private String text;

    private int slideOrder;
}
