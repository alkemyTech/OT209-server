package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.Testimonial;
import com.alkemy.ong.models.mapper.TestimonialMapper;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;
    @Autowired
    private TestimonialMapper testimonialMapper;

    @Transactional
    @Override
    public void delete(Long id) {
        Testimonial t = findById(id);
        t.setSoftDelete(Boolean.TRUE);
        testimonialRepository.save(t);
    }

    @Override
    public TestimonialResponse save(TestimonialRequest request) {
        try {
            if (request.getName().isBlank() || request.getName().isEmpty()
                    || request.getContent().isBlank() || request.getContent().isEmpty()) {
                throw new RuntimeException("does not support blank name or content");
            }
            return testimonialMapper.toDto(testimonialRepository.save(testimonialMapper.toTestimonial(request)));
        } catch (Exception e) {

            throw new RuntimeException("error trying to save" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public TestimonialResponse update(Long id, TestimonialRequest request) {        
        Testimonial tSaved = testimonialRepository.save(testimonialMapper.updateDto(findById(id), request));
        return testimonialMapper.toDto(tSaved);
    }

    @Override
    public Testimonial findById(Long id) {
        return testimonialRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched testimonial does not exist"));
    }

}
