package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlidesGetDTO {

    private String imageUrl;
    private Integer order_ong;

    public SlidesGetDTO() {
        super();
    }

    public SlidesGetDTO(String imageUrl, Integer order_ong) {
        super();
        this.imageUrl = imageUrl;
        this.order_ong = order_ong;
    }
}
