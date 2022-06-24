package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.PageTestimonialResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.service.TestimonialService;
import javax.validation.Valid;
import io.swagger.annotations.*;
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
    @ApiOperation(value = "Create Testimonials", notes = "Allows user to insert new testimonial ")
    @ResponseStatus(HttpStatus.CREATED)
    public TestimonialResponse create(@Valid @RequestBody @ApiParam(
            name = "New Testimonial",
            value = "New testimonial to save",
            required = true) TestimonialRequest request) {
        return testimonialService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Update testimonials by ID", notes = "Allows user to update an existing testimonials by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Testimonial updated!"),
        @ApiResponse(code = 404, message = "No Testimonial with requested ID is found"),})
    public TestimonialResponse update(@PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the testimonial requested",
                    example = "1",
                    required = true) Long id,
            @RequestBody
            @ApiParam(
                    name = "New Testimonials",
                    value = "testimonials to save",
                    required = true) TestimonialRequest request) {
        return testimonialService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Soft delete testimonial by id", notes = "Allows user to delete testimonial by ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Testimonial soft deleted!"),
        @ApiResponse(code = 404, message = "No testimonial with requested ID is found"),})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the testimonial requested",
                    example = "1",
                    required = true) Long id) {
        testimonialService.delete(id);
    }

    @GetMapping()
    @ApiOperation(value = "Get All Testimonials", notes = "Returns all Testimonials")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Returns all Testimonials")
    })
    public ResponseEntity<PageTestimonialResponse> getAll(
            @RequestParam(value = "page")
            @ApiParam(
                    name = "page",
                    type = "Integer",
                    value = "Page requested from the list of testimonial (starting in 1)",
                    example = "1",
                    required = true) int offset,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        PageTestimonialResponse response = testimonialService.getTestimonials(offset, uriComponentsBuilder.path(TESTIMONIALS_PATH));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Testimonial By ID", notes = "Returns one testimonial by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Return the requested testimonials"),
        @ApiResponse(code = 404, message = "No testimonials with requested ID is found"),})
    public ResponseEntity<TestimonialResponse> getDetail(@PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the testimonial requested",
                    example = "1",
                    required = true) Long id) {
        TestimonialResponse response = testimonialService.getTestimonial(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
