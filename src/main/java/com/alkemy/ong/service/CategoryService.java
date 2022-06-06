package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.models.response.CategoryResponse;

public interface CategoryService{

	public void delete(Long id);

	public List<CategoryBasicResponse> getCategories();
	
	public CategoryResponse saveCategory(CategoryRequest category);
	
	public CategoryResponse updateCategory(Long id, CategoryRequest category);
	
	public CategoryResponse getCategory(Long id);
	
}
