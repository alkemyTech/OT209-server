package com.alkemy.ong.service;

import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsResponse;
import com.alkemy.ong.models.response.PageNewsResponse;
import org.springframework.web.util.UriComponentsBuilder;

public interface NewsService {

    boolean itExists(Long id);
    PageNewsResponse getAll(int offset, UriComponentsBuilder uriComponentsBuilder);
    NewsResponse getById(Long id);
    NewsResponse create(NewsRequest request);
    NewsResponse updateById(Long id, NewsRequest request);
    void deleteById(Long id);
}
