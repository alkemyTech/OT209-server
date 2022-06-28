package com.alkemy.ong.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ApiModel(value = "Members Request", description = "Format for creating and updating Members")
public class MemberRequest {
        @NotBlank
        @NotNull(message = "the name can't be null")
        @NotEmpty(message = "the name can't be empty")
        @Column(nullable = false)
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "The name has to contain only letters")
        @ApiModelProperty(name = "Name",
                value = "Title of the Member",
                dataType = "String",
                notes = "It can't be null",
                example = "Miembro insertado con exito parra Somos Más",
                required = true)
        private String name;
        @NotNull(message = "the Image can't be null")
        @NotEmpty(message = "the Image can't be empty")
        @ApiModelProperty(name = "Image",
                value = "Emblematic image of the member",
                dataType = "String",
                notes = "It can't be null",
                example = "image0001.jpg",
                required = true)
        private String image;
        @NotNull(message = "description can't be null")
        @NotEmpty(message = "description can't be empty")
        @ApiModelProperty(name = "Description",
                value = "Content of the description",
                dataType = "String",
                notes = "It can't be null",
                example = "Lorem Ipsum...",
                required = true)
        private String description;
}
