package com.alkemy.ong.models.response;

import com.alkemy.ong.models.entity.NewsEntity;
import com.alkemy.ong.models.entity.UserEntity;
import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class CommentDetailsResponse {

    private Long id;
    private String body;
    private UserEntity user;
    private NewsEntity news;
    private Timestamp date;

}
