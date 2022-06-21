package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberBasicResponse {

    @ApiModelProperty(name = "Name",
            value = "Title of the member",
            dataType = "String",
            example = "Imsercion de miembro para Somos Más")
    String name;

}
