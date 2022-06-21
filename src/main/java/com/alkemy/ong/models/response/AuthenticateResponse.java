package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "Authentication response", description = "Response with email and token generated for user logged in")
public class AuthenticateResponse {

    @ApiModelProperty(name = "email",
            value = "User email",
            dataType = "String",
            example = "email@server.domain")
    private String email;
    @ApiModelProperty(name = "token",
            value = "User token",
            dataType = "String",
            example = "Token generated")
    private String token;
}
