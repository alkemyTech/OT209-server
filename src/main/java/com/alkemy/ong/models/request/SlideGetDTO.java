package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideGetDTO {

    private String imageUrl;
    private Integer order;
    private String text;
    private Long organizationId;

    public SlideGetDTO() {
    }
}
