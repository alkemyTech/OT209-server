package com.alkemy.ong.models.request;

import com.alkemy.ong.models.response.CategoryResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(value = "News Request", description = "Format for creating and updating news")
public class NewsRequest {
	
    @NotBlank
    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name can't be empty")
    @ApiModelProperty(name = "Name",
                        value = "Title of the news",
                        dataType = "String",
                        notes = "It can't be null",
                        example = "Éxito en el concierto organizado por Somos Más",
                        required = true)
    private String name;

    @NotBlank
    @NotNull(message = "Content can't be null")
    @NotEmpty(message = "Content can't be empty")
    @ApiModelProperty(name = "Content",
                        value = "Content of the news",
                        dataType = "String",
                        notes = "It can't be null",
                        example = "Lorem Ipsum...",
                        required = true)
    private String content;

    @NotBlank
    @NotNull(message = "Image can't be null")
    @NotEmpty(message = "Image can't be empty")
    @ApiModelProperty(name = "Image",
                        value = "Emblematic image of the news",
                        dataType = "String",
                        notes = "It can't be null",
                        example = "image0001.jpg",
                        required = true)
    private String image;

    @NotNull(message = "CategoryId can't be null")
    @ApiModelProperty(name = "Name",
                        value = "ID of the category at which the news belongs",
                        dataType = "Category Object",
                        notes = "It can't be null",
                        example = "1",
                        required = true)
    private Long categoryId;
}
