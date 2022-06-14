package com.alkemy.ong.service;

import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsDetailsResponse;
import com.alkemy.ong.models.response.PageNewsResponse;
import org.springframework.web.util.UriComponentsBuilder;

public interface NewsService {

    boolean itExists(Long id);
    PageNewsResponse getAll(int offset, UriComponentsBuilder uriComponentsBuilder);
    NewsDetailsResponse getById(Long id);
    NewsDetailsResponse create(NewsRequest request);
    NewsDetailsResponse updateById(Long id, NewsRequest request);
    void deleteById(Long id);
}
