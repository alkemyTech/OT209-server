package com.alkemy.ong.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsBasicResponse {

    private Long id;
    private String name;
    private String content;
    private String image;
    private CategoryResponse category;
}
