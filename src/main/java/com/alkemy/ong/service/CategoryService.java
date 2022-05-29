package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.models.response.CategoryBasicResponse;

public interface CategoryService{

	public void delete(Long id);

	public List<CategoryBasicResponse> getCategories();
}
