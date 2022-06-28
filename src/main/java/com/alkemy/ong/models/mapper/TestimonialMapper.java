
package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.Testimonial;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Component
public class TestimonialMapper {
    
    
    public Testimonial toTestimonial(TestimonialRequest request){
        return Testimonial.builder()
                .name(request.getName())
                .content(request.getContent())
                .image(request.getImage())
                .date(LocalDateTime.now())
                .softDelete(Boolean.FALSE)
                .build();
    }
    
    public TestimonialResponse toDto(Testimonial testimonial){
        return TestimonialResponse.builder()
                .id(testimonial.getId())
                .name(testimonial.getName())
                .image(testimonial.getImage())
                .content(testimonial.getContent())
                .date(testimonial.getDate().toLocalDate())
                .hour(testimonial.getDate().toLocalTime())
                .build();
    }
    public Testimonial updateDto(Testimonial t,TestimonialRequest request){
        t.setName(request.getName());
        t.setContent(request.getContent());
        t.setImage(request.getImage());
        t.setDate(LocalDateTime.now());
        return t;
    }
    
    public List<TestimonialResponse> testimonialEntityList2DTOList(List<Testimonial> entities){
        List<TestimonialResponse> dtos = new ArrayList<>();

        for (Testimonial testimonialEntity : entities) {
            TestimonialResponse basicDto = new TestimonialResponse();
            basicDto.setId(testimonialEntity.getId());
            basicDto.setName(testimonialEntity.getName());
            basicDto.setImage(testimonialEntity.getImage());
            basicDto.setContent(testimonialEntity.getContent());
            basicDto.setDate(testimonialEntity.getDate().toLocalDate());
            basicDto.setHour(testimonialEntity.getDate().toLocalTime());
            dtos.add(basicDto);

        }
        return dtos;

    }
}
