package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SlideRequestDTO {

    private String imageUrl;
    private Integer order;
    private String text;

    @NotNull(message = "Organization id is mandatory")
    private Long organizationId;


    @NotBlank(message = "Encode is mandatory")
    private String encodeImg;

    public SlideRequestDTO() {
    }
}
