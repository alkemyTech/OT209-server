package com.alkemy.ong.models.response;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Testimonial Response", description = "response with some properties of the requested Testimonials")
public class TestimonialResponse {

    @ApiModelProperty(name = "ID",
            value = "News ID",
            dataType = "Long",
            example = "1")
    private Long id;

    @ApiModelProperty(name = "Name",
            value = "Title of the news",
            dataType = "String",
            example = "Thanks")
    private String name;

    @ApiModelProperty(name = "Image",
            value = "image of the testimonials",
            dataType = "String",
            example = "code base 64")
    private String image;

    @ApiModelProperty(name = "Content",
            value = "Content of the news",
            dataType = "String",
            example = "Thank you ong we are more for helping me")
    private String content;

    @ApiModelProperty(name = "date",
            value = "date at the time of creation of testimonial",
            dataType = "String",
            example = "2022/11/11")
    private LocalDate date;

    //separa las horas del dìa
    private LocalTime hour;
}
