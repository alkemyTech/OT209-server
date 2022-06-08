package com.alkemy.ong.models.request;

import com.alkemy.ong.models.entity.NewsEntity;
import com.alkemy.ong.models.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
public class CommentRequest {

    private Long id;

    @NotBlank
    @NotNull(message = "The body can't be null.")
    @NotEmpty(message = "The body can't be empty.")
    private String body;

    @NotBlank
    @NotNull(message = "The body can't be null.")
    @NotEmpty(message = "The body can't be empty.")
    private UserEntity user;

 //   private NewsEntity news;

    private Timestamp date;

}
