package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.ActivityRequest;;
import com.alkemy.ong.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/ong")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activities")
    public String save(@Valid @RequestBody ActivityRequest activityRequest) {

        boolean responseContent = (activityRequest.getContent()).matches("(^[[a-zA-Z]+(\\-|\\ )?]+)$");
        boolean responseName = (activityRequest.getName()).matches("(^[[a-zA-Z]+(\\-|\\ )?]+)$");
        String response="";
        try {
            if(responseContent && responseName) {
                activityService.saveActivity(activityRequest);
                response="insertion Succesfull";
            }else {
                throw new IllegalArgumentException("Ilegal arguments");
            }
        } catch (IllegalArgumentException e) {
            response= e.getMessage();
        }
        return response;
    }
}
