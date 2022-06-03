package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.NewsEntity;
import com.alkemy.ong.models.mapper.NewsMapper;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsDetailsResponse;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsMapper newsMapper;


    @Override
    public boolean itExists(Long id) {
        return newsRepository.findById(id).isPresent();
    }

    @Override
    public NewsDetailsResponse getById(Long id) {
        return newsMapper.newsEntity2NewsResponse(newsRepository.findById(id).orElseThrow());
    }

    @Override
    public NewsDetailsResponse create(NewsRequest request) {
        return newsMapper.newsEntity2NewsResponse(newsRepository.save(newsMapper.newsRequest2NewsEntity(request)));
    }

    @Override
    public NewsDetailsResponse updateById(Long id, NewsRequest request) {
        NewsEntity entity = newsRepository.findById(id).orElseThrow();
        return newsMapper.newsEntity2NewsResponse(newsRepository.save(newsMapper.updateNewsEntityWithNewsRequest(entity, request)));
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
