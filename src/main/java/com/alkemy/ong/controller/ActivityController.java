package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;
import com.alkemy.ong.service.ActivityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/ong")
@CrossOrigin("*")
@RequiredArgsConstructor
@Api(value = "Activity controller", description = "This API has a Activity for users")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activities")
    public String save(@Valid @RequestBody ActivityRequest activityRequest) {

        boolean responseContent = (activityRequest.getContent()).matches("(^[[a-zA-Z]+(\\-|\\ )?]+)$");
        boolean responseName = (activityRequest.getName()).matches("(^[[a-zA-Z]+(\\-|\\ )?]+)$");
        String response = "";
        try {
            if (responseContent && responseName) {
                activityService.saveActivity(activityRequest);
                response = "insertion Succesfull";
            } else {
                throw new IllegalArgumentException("Ilegal arguments");
            }
        } catch (IllegalArgumentException e) {
            response = e.getMessage();
        }
        return response;
    }

    @PutMapping("/activities/{id}")
    public ResponseEntity<ActivityResponse> update(@PathVariable Long id, @Valid @RequestBody ActivityRequest activityRequest) {

        return activityService.itExists(id)
                ? ResponseEntity.status(HttpStatus.OK).body(activityService.updateActivity(id, activityRequest))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
