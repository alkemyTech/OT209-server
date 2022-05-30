package com.alkemy.ong.controller;


import com.alkemy.ong.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ActivitiesController {
    @Autowired
    ActivitiesService activitiesService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/activities/{id}")
    public <ActivitiesDto> ResponseEntity<?>  updateActivities(@PathVariable Long id, @RequestBody ActivitiesDto activities) throws Exception {
        activitiesService.updateActivities(id,Activities);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(id)
                .toUri();
        return new ResponseEntity<>(location,(HttpStatus.ACCEPTED));
    }


}
