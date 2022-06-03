package com.alkemy.ong.service;

import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;

public interface ActivityService {

    public ActivityResponse saveActivity(ActivityRequest activity);
}
