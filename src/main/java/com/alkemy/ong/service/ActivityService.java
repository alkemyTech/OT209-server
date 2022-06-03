package com.alkemy.ong.service;

import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;

public interface ActivityService {

    public boolean itExists(Long id);
    public ActivityResponse saveActivity(ActivityRequest activity);
    public ActivityResponse updateActivity(Long id, ActivityRequest request);
}
