package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.NewsEntity;
import com.alkemy.ong.models.mapper.NewsMapper;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.*;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.utility.PaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsMapper newsMapper;

    @Override
    public boolean itExists(Long id) {
        System.out.println(id);
        return newsRepository.findById(id).isPresent();
    }

    @Override
    public PageNewsResponse getAll(int offset, UriComponentsBuilder uriComponentsBuilder) {
        Page<NewsEntity> dataPage = newsRepository.findAll(PageRequest.of((offset - 1), 10));

        System.out.println(newsRepository.findAll());

        List<NewsResponse> dtos = newsMapper.newsEntityList2NewsBasicResponseList(dataPage.getContent());

        PaginationHelper uriUtil = new PaginationHelper(uriComponentsBuilder, dataPage.getTotalPages(), offset);

        return new PageNewsResponse(dtos, uriUtil.getUriPrev(), uriUtil.getUriNext());
    }

    @Override
    public NewsResponse getById(Long id) {
        return newsMapper.newsEntity2NewsResponse(newsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched news does not exist")));
    }

    @Override
    public NewsResponse create(NewsRequest request) {
        return newsMapper.newsEntity2NewsResponse(newsRepository.save(newsMapper.newsRequest2NewsEntity(request)));
    }

    @Override
    public NewsResponse updateById(Long id, NewsRequest request) {
        NewsEntity entity = newsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched news does not exist"));
        return newsMapper.newsEntity2NewsResponse(newsRepository.save(newsMapper.updateNewsEntityWithNewsRequest(entity, request)));
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
