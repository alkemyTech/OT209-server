package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Register Response", description = "Response for the user generated on database")
public class RegisterResponse {

    @ApiModelProperty(name = "id",
            value = "User id",
            dataType = "Long",
            example = "1")
    private Long id;
    @ApiModelProperty(name = "firstName",
            value = "User first name",
            dataType = "String",
            example = "Juan")
    private String firstName;
    @ApiModelProperty(name = "lastName",
            value = "User last name",
            dataType = "String",
            example = "Bodoque")
    private String lastName;
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
