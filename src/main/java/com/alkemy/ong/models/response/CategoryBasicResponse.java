package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Category Response (Basic)", description = "Response with some properties of the requested category")
public class CategoryBasicResponse {

    @ApiModelProperty(name = "Name",
            value = "Title of the category",
            dataType = "String",
            example = "Relevante")
    String name;
}
