package com.alkemy.ong.models.request;

import com.alkemy.ong.models.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
public class NewsRequest {

    private Long id;

    @NotBlank
    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotBlank
    @NotNull(message = "Content can't be null")
    @NotEmpty(message = "Content can't be empty")
    private String content;

    @NotBlank
    @NotNull(message = "Image can't be null")
    @NotEmpty(message = "Image can't be empty")
    private String image;

    @NotBlank
    @NotNull(message = "Image can't be null")
    @NotEmpty(message = "Image can't be empty")
    private Long categoryId;
}
