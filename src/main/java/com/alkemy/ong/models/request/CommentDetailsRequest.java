package com.alkemy.ong.models.request;

import com.alkemy.ong.models.entity.NewsEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class CommentDetailsRequest {

    private Long id;
    @NotBlank
    @NotNull(message = "The body can't be null.")
    @NotEmpty(message = "The body can't be empty.")
    private String body;
    @NotNull
    private Long userID;
    private NewsEntity news;
    private Timestamp date;

}
