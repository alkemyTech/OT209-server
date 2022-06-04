package com.alkemy.ong.models.mapper;
import com.alkemy.ong.models.entity.ActivityEntity;
import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.ActivityResponse;
import com.alkemy.ong.models.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {
    public ActivityEntity activityDTO2Entity(ActivityRequest dto) {
        ActivityEntity entity = new ActivityEntity();
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        return entity;
    }

    public ActivityResponse activityEntity2DTO(ActivityEntity entity) {
        return ActivityResponse.builder()
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                .timestamp(entity.getTimestamp())
                .id(entity.getId())
                .build();
    }

    public ActivityEntity activityEntityRefreshValues(ActivityEntity entity, ActivityRequest dto){
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());

        return entity;
    }
}