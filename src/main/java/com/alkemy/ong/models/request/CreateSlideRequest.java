package com.alkemy.ong.models.request;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateSlideRequest {
    @NotBlank(message = "File encoded in base64 must be provided.")
    private String fileEncodeBase64;

    @Nullable
    private Integer order;

}
