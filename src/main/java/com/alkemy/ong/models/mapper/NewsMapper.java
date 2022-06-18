package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.entity.NewsEntity;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.models.response.NewsBasicResponse;
import com.alkemy.ong.models.response.NewsDetailsResponse;
import com.alkemy.ong.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public List<NewsBasicResponse> newsEntityList2NewsBasicResponseList(List<NewsEntity> entities){
        List<NewsBasicResponse> dtos = new ArrayList<>();

        for (NewsEntity entity : entities) {
            NewsBasicResponse basicDto = new NewsBasicResponse();
            basicDto.setName(entity.getName());
            basicDto.setId(entity.getId());
            basicDto.setImage(entity.getImage());
            basicDto.setContent(entity.getContent());
            basicDto.setCategory(categoryMapper.categoryEntity2DTO(entity.getCategory()).getName());
            dtos.add(basicDto);

        }
        return dtos;

    }

    public NewsEntity newsRequest2NewsEntity (NewsRequest request){

        NewsEntity entity = new NewsEntity();
        entity.setName(request.getName());
        entity.setImage(request.getImage());
        entity.setContent(request.getContent());
        entity.setCategoryId(request.getCategoryId());
        entity.setCategory(categoryRepository.getById(entity.getCategoryId()));
        return entity;
    }

    public NewsDetailsResponse newsEntity2NewsResponse (NewsEntity entity){

        System.out.println(entity.getCategory());

        return NewsDetailsResponse.builder()
                .name(entity.getName())
                .image(entity.getImage())
                .content(entity.getContent())
                .categoryId(entity.getCategoryId())
                .category(categoryMapper.categoryEntity2DTO(entity.getCategory()))
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
