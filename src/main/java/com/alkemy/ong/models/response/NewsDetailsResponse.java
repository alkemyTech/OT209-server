package com.alkemy.ong.models.response;

import com.alkemy.ong.models.entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class NewsDetailsResponse {

    private Long id;
    private String name;
    private String content;
    private String image;
    private CategoryEntity category;
    private Long categoryId;
    private Timestamp timestamp;

}
