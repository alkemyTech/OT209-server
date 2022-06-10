package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.NewsEntity;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public NewsEntity newsRequest2NewsEntity (NewsRequest request){
        NewsEntity entity = new NewsEntity();
        entity.setName(request.getName());
        entity.setImage(request.getImage());
        entity.setContent(request.getContent());
        entity.setCategoryId(request.getCategoryId());
        return entity;
    }

    public NewsDetailsResponse newsEntity2NewsResponse (NewsEntity entity){
        return NewsDetailsResponse.builder()
                .name(entity.getName())
                .image(entity.getImage())
                .content(entity.getContent())
                .categoryId(entity.getCategoryId())
                .category(entity.getCategory())
                .timestamp(entity.getTimestamp())
                .id(entity.getId())
                .build();
    }
//newsMapper.newsEntity2NewsResponse(newsRepository.save(newsMapper.updateNewsEntityWithNewsRequest(entity, request)));
    public NewsEntity updateNewsEntityWithNewsRequest (NewsEntity entity, NewsRequest request){
        entity.setName(request.getName());
        entity.setImage(request.getImage());
        entity.setContent(request.getContent());
        entity.setCategoryId(request.getCategoryId());
        return entity;
    }

}
