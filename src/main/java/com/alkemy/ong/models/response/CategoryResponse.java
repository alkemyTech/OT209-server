package com.alkemy.ong.models.response;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel(value = "Category Response", description = "Response with the requested category")
public class CategoryResponse {

    @ApiModelProperty(name = "Name",
            value = "Title of the category",
            dataType = "String",
            example = "Relevante")
    private String name;

    @ApiModelProperty(name = "Description",
            value = "Description of the category",
            dataType = "String",
            example = "Lorem Ipsum..."
    )
    private String description;

    @ApiModelProperty(name = "Image",
            value = "Image of the category",
            dataType = "String",
            example = "image0001.jpg"
    )
    private String image;

    @CreationTimestamp
    private Timestamp timestamp;
}
