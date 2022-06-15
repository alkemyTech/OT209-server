
package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.PageTestimonialResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.service.TestimonialService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin("*")
@RequestMapping("/testimonials")
@RequiredArgsConstructor
public class TestimonialController {

    private static final String TESTIMONIALS_PATH = "/testimonials";

    private final TestimonialService testimonialService;
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TestimonialResponse create(@Valid @RequestBody TestimonialRequest request){
        return testimonialService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TestimonialResponse update(@PathVariable Long id, @RequestBody TestimonialRequest request){
       return testimonialService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        testimonialService.delete(id);
    }

    @GetMapping
    public ResponseEntity<PageTestimonialResponse> getAll(
            @RequestParam(value = "page") int offset,
            UriComponentsBuilder uriComponentsBuilder
    ){

        PageTestimonialResponse response = testimonialService.getTestimonials(offset, uriComponentsBuilder.path(TESTIMONIALS_PATH));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestimonialResponse> getDetail(@PathVariable Long id){
        TestimonialResponse response = testimonialService.getTestimonial(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
