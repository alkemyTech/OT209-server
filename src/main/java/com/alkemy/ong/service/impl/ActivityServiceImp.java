package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.ActivityEntity;
import com.alkemy.ong.models.mapper.ActivityMapper;
import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public boolean itExists(Long id) {
        return activityRepository.findById(id).isPresent();
    }

    @Override
    @Transactional
    public ActivityResponse saveActivity(ActivityRequest activity) {

        ActivityEntity entity = activityMapper.activityDTO2Entity(activity);
        activityRepository.save(entity);

        return activityMapper.activityEntity2DTO(entity);
    }

    @Override
    @Transactional
    public ActivityResponse updateActivity(Long id, ActivityRequest request) {

        ActivityEntity entity = activityRepository.findById(id).orElseThrow();

        return activityMapper.activityEntity2DTO(activityRepository.save(activityMapper.activityEntityRefreshValues(entity, request)));
    }
}
