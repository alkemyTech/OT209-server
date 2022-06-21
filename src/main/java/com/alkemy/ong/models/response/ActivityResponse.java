package com.alkemy.ong.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class ActivityResponse {

    private String name;
    private String content;
    private String image;
    @CreationTimestamp
    private Timestamp timestamp;
    private Boolean softDelete = Boolean.FALSE;
    private Long id;
}
