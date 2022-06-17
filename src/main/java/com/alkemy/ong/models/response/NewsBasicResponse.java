package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "News Response (Basic)", description = "Response with some properties of the requested news")
public class NewsBasicResponse {

    @ApiModelProperty(name = "ID",
                        value = "News ID",
                        dataType = "Long",
                        example = "1")
    private Long id;

    @ApiModelProperty(name = "Name",
                        value = "Title of the news",
                        dataType = "String",
                        example = "Éxito en el concierto organizado por Somos Más")
    private String name;

    @ApiModelProperty(name = "Content",
                        value = "Content of the news",
                        dataType = "String",
                        example = "Lorem Ipsum...")
    private String content;

    @ApiModelProperty(name = "Category",
                        value = "Category at which the news belongs",
                        dataType = "String",
                        example = "Events")
    private String image;

    @ApiModelProperty(name = "Category",
                        value = "Category at which the news belongs",
                        dataType = "String",
                        example = "Events")
    private String category;
}
