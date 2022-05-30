package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivitiesDto;
import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActivitiesService {
    ResponseEntity<?> createActivity(ActivitiesDto activity);
    public List< ActivitiesDto> getAll();
    public ActivitiesDto getById(Long id) throws Exception;
    void updateActivities(Long id, ActivitiesDto activity) throws Exception;

    Activities findById(Long id);

}
