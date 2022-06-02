package com.alkemy.ong.service;

import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.models.response.NewsDetailsResponse;

import java.util.List;

public interface NewsService {

    boolean itExists(Long id);
    NewsDetailsResponse getById(Long id);
    NewsDetailsResponse create(NewsRequest request);
    NewsDetailsResponse updateById(Long id, NewsRequest request);
    void deleteById(Long id);
}
