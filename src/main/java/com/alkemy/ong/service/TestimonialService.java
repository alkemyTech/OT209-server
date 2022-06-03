package com.alkemy.ong.service;

import com.alkemy.ong.models.entity.Testimonial;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialResponse;


public interface TestimonialService {
        void delete(Long id);
        public TestimonialResponse save(TestimonialRequest request);
        public TestimonialResponse update(Long id,TestimonialRequest request);
        public Testimonial findById(Long id);
}
