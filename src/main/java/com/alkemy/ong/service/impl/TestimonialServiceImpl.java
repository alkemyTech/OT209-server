package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.models.entity.Testimonial;
import com.alkemy.ong.models.mapper.TestimonialMapper;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.PageTestimonialResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.AmazonClient;
import com.alkemy.ong.service.TestimonialService;
import javax.transaction.Transactional;
import com.alkemy.ong.utility.PaginationHelper;
import com.alkemy.ong.utils.ImageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;
    @Autowired
    private TestimonialMapper testimonialMapper;
    @Autowired
    private ImageHelper imageHelper;
    @Autowired
    private AmazonClient amazonClient;

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
            String nameFile = "testimonial_" + request.getName() + new Random().longs().toString()+".png";
            MultipartFile multiparte = imageHelper.base64ToImage(request.getImage(), nameFile);
            String amazonUrl = amazonClient.uploadFile(multiparte);
            request.setImage(amazonUrl);

            return testimonialMapper.toDto(testimonialRepository.save(testimonialMapper.toTestimonial(request)));

        } catch (Exception e) {

            throw new RuntimeException("error trying to save" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public TestimonialResponse update(Long id, TestimonialRequest request) {
    	String nameFile = "testimonial_" + request.getName() + new Random().longs().toString()+".png";
    	try {
    		MultipartFile multiparte = imageHelper.base64ToImage(request.getImage(), nameFile);
    		String amazonUrl = amazonClient.uploadFile(multiparte);
    		request.setImage(amazonUrl);			
		} catch (Exception e) {
			throw new RuntimeException("error trying to save" + e.getMessage());
		}
        Testimonial tSaved = testimonialRepository.save(testimonialMapper.updateDto(findById(id), request));
        return testimonialMapper.toDto(tSaved);
    }

    @Override
    public Testimonial findById(Long id) {
        return testimonialRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched testimonial does not exist"));
    }

    @Override
    public PageTestimonialResponse getTestimonials(int offset, UriComponentsBuilder uriComponentsBuilder) {

        Page<Testimonial> dataPage = testimonialRepository.findAll(PageRequest.of((offset - 1), 10));
        List<TestimonialResponse> dtos = testimonialMapper.testimonialEntityList2DTOList(dataPage.getContent());

        PaginationHelper uriUtil = new PaginationHelper(uriComponentsBuilder, dataPage.getTotalPages(), offset);

        return new PageTestimonialResponse(dtos, uriUtil.getUriPrev(), uriUtil.getUriNext());
    }

    @Override
    public TestimonialResponse getTestimonial(Long id) {
        Optional<Testimonial> entity = testimonialRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound(String.format("Id %s not found in testimonials", id));
        }
        return testimonialMapper.toDto(entity.get());
    }

}
