package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.NewsEntity;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper {

    public List<NewsResponse> newsEntityList2NewsBasicResponseList(List<NewsEntity> entities){
        List<NewsResponse> dtos = new ArrayList<>();

        for (NewsEntity entity : entities) {
            dtos.add(NewsResponse.builder()
                    .name(entity.getName())
                    .image(entity.getImage())
                    .content(entity.getContent())
                    .categoryId(entity.getCategoryId())
                    .id(entity.getId())
                    .timestamp(entity.getTimestamp())
                    .build());
        }
        return dtos;
    }

    public NewsEntity newsRequest2NewsEntity (NewsRequest request){

        NewsEntity entity = new NewsEntity();
        entity.setName(request.getName());
        entity.setImage(request.getImage());
        entity.setContent(request.getContent());
        entity.setCategoryId(request.getCategoryId());
        return entity;
    }

    public NewsResponse newsEntity2NewsResponse (NewsEntity entity){

        System.out.println(entity.getCategory());

        return NewsResponse.builder()
                .name(entity.getName())
                .image(entity.getImage())
                .content(entity.getContent())
                .categoryId(entity.getCategoryId())
                .id(entity.getId())
                .timestamp(entity.getTimestamp())
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
