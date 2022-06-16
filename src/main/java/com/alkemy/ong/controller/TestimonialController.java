
package com.alkemy.ong.controller;


import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.PageTestimonialResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.service.TestimonialService;
import javax.validation.Valid;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import com.alkemy.ong.auth.config.SwaggerConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin("*")
@RequestMapping("/testimonials")
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.TESTIMONIAL_CONTROLLER})
public class TestimonialController {

    private static final String TESTIMONIALS_PATH = "/testimonials";

    private final TestimonialService testimonialService;
    
    @PostMapping("")
    @ApiOperation(value = "Create Testimonials", notes = "Allows user to insert news testimonial ")
    @ResponseStatus(HttpStatus.CREATED)
    public TestimonialResponse create(@Valid @RequestBody @ApiParam(
            name= "New Testimonial",
            value = "New testimonial to save",
            required = true)
         TestimonialRequest request){
        return testimonialService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Update testimonials by ID",notes = "Allows user to update an existing testimonials by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Testimonial updated!"),
            @ApiResponse(code = 404, message = "No Testimonial with requested ID is found"),
    })
    public TestimonialResponse update(@PathVariable Long id,
                                    @RequestBody TestimonialRequest request){
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
