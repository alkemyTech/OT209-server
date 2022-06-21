package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
public class MemberResponse {

    @ApiModelProperty(name = "Juan",
            value = "name of the Member",
            dataType = "String",
            example = "Juan")
    private String name;
    @ApiModelProperty(name = "Description",
            value = "Description of the Member",
            dataType = "String",
            example = "lorem ipsu...")
    private String description;
    @ApiModelProperty(name = "Image",
            value = "image of the member",
            dataType = "String",
            example = "imgage.img")
    private String image;
}
