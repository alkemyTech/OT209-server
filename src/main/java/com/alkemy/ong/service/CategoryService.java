package com.alkemy.ong.service;

import java.util.List;

import org.springframework.web.util.UriComponentsBuilder;

import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.models.response.PageCategoryResponse;

public interface CategoryService{

	public void delete(Long id);

	public PageCategoryResponse getCategories(int offset, UriComponentsBuilder uriComponentsBuilder, String path);
	
	public CategoryResponse saveCategory(CategoryRequest category);
	
	public CategoryResponse updateCategory(Long id, CategoryRequest category);
	
	public CategoryResponse getCategory(Long id);
	
}
