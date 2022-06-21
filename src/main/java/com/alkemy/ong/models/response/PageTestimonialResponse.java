package com.alkemy.ong.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PageTestimonialResponse {

    List<TestimonialResponse> testimonials;
    private String urlPrevPage;
    private String urlNextPage;
}
