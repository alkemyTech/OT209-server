package com.alkemy.ong.controller;


import com.alkemy.ong.exception.NotAcceptableArgumentException;
import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.models.request.SlideGetDTO;
import com.alkemy.ong.models.request.SlideRequestDTO;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Slides")
public class SlidesController {

    @Autowired
    SlideService slideService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SlideRequestDTO slideDTO) {

        SlideGetDTO slide;
        try {
            slide= this.slideService.create(slideDTO);
        } catch (NotAcceptableArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(slide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SlideRequestDTO slideRequestDTO)
    {
        Map<String, Object> response=new HashMap<>();
        SlideGetDTO slide;
        try {
            slide= slideService.updateSlide(id,slideRequestDTO);
            response.put(null,slide);
        } catch (OrgNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch( NotAcceptableArgumentException | IOException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(slide);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable Long id){

        try {
            String msg= slideService.delete(id);
            return ResponseEntity.ok().body(msg);
        } catch (OrgNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
