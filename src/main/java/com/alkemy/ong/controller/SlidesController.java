package com.alkemy.ong.controller;

import com.alkemy.ong.auth.config.SwaggerConfig;
import com.alkemy.ong.exception.NotAcceptableArgumentException;
import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.models.request.SlideGetDTO;
import com.alkemy.ong.models.request.SlideRequestDTO;
import com.alkemy.ong.models.response.SlideBasicResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.service.SlideService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Slides")
@Api(tags = {SwaggerConfig.SLIDES_CONTROLLER})

public class SlidesController {

    @Autowired
    SlideService slideService;

    @ApiOperation(value = "Create slide", notes = "Create a new slide for an organization")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Slide created!", response = SlideRequestDTO.class)
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SlideRequestDTO slideDTO) {

        SlideGetDTO slide;
        try {
            slide = this.slideService.create(slideDTO);
        } catch (NotAcceptableArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(slide);
    }

    @ApiOperation(value = "Update slide", notes = "Update a slide by it's id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Slide updated!", response = SlideRequestDTO.class),
        @ApiResponse(code = 403, message = "ERROR - id does not exist on database")})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SlideRequestDTO slideRequestDTO) {
    	System.out.println("entrando");
        Map<String, Object> response = new HashMap<>();
        SlideGetDTO slide;
        try {
            slide = slideService.updateSlide(id, slideRequestDTO);
            response.put(null, slide);
            System.out.println("dentro del try");
        } catch (OrgNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotAcceptableArgumentException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(slide);

    }

    @ApiOperation(value = "Delete slide", notes = "Delete a slide by it's id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Slide deleted!"),
        @ApiResponse(code = 403, message = "ERROR - id does not exist on database")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable Long id) {

        try {
            String msg = slideService.delete(id);
            return ResponseEntity.ok().body(msg);
        } catch (OrgNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Get slides", notes = "Returns all slides")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Slides requested!", response = SlideBasicResponse.class),
    })
    @GetMapping
    public ResponseEntity<List<SlideBasicResponse>> getSlides() {
        List<SlideBasicResponse> response = slideService.getSlides();
        return ResponseEntity.ok(response);
    }
    
    @ApiOperation(value = "Get one slide", notes = "Returns one slide by it's id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Slide requested!", response = SlideBasicResponse.class),
        @ApiResponse(code = 400, message = "ERROR - id does not exist on database")})
    @GetMapping("/{id}")
    public ResponseEntity<SlideResponse> getSlide(@PathVariable Long id) {
    	System.out.println(id);
        SlideResponse response = slideService.getSlideById(id);
        return ResponseEntity.ok(response);
    }
}
