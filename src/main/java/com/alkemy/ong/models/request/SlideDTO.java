package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideDTO {

    private long id;

    private String imageUrl;

    private Integer order;

    private String text;

    private Long organizationId;

    public SlideDTO(Long id, String imageUrl, Integer order, String text, Long organizationId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.order = order;
        this.text = text;
        this.organizationId = organizationId;
    }
    public SlideDTO() {
    }
}
