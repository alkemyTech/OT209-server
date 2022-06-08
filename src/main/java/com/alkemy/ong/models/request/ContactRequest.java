package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContactRequest {

    @NotEmpty(message = "Name can't be empty")
    @NotBlank
    @NotNull(message = "Name can't be null")
    private String name;
    @NotEmpty(message = "Email can't be empty")
    @NotBlank
    @NotNull(message = "Email can't be null")
    private String email;
    private String message;
    private String phone;

}
