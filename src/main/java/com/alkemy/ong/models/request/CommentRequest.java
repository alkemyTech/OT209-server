package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;
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
    private Long userId;

    @NotNull(message = "id news is required")
    private Long newsId;

 //   private NewsEntity news;

    private Timestamp date;

}
