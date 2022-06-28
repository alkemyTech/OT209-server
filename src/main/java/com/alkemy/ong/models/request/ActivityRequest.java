package com.alkemy.ong.models.request;

import lombok.Setter;
import lombok.Getter;
@Getter
@Setter
public class ActivityRequest {

    private Long id;
    private String name;
    private String content;
    private String image;

}
