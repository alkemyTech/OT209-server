package com.alkemy.ong.service;

import com.alkemy.ong.models.entity.Testimonial;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.PageTestimonialResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import org.springframework.web.util.UriComponentsBuilder;


public interface TestimonialService {
        void delete(Long id);
        public TestimonialResponse save(TestimonialRequest request);
        public TestimonialResponse update(Long id,TestimonialRequest request);
        public Testimonial findById(Long id);
        public PageTestimonialResponse getTestimonials(int offset, UriComponentsBuilder uriComponentsBuilder);
        public TestimonialResponse getTestimonial(Long id);
}
