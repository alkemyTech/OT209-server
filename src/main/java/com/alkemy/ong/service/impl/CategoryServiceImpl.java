package com.alkemy.ong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.mapper.CategoryMapper;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<CategoryBasicResponse> getCategories() {
		List<CategoryEntity> entities = categoryRepository.findAll();
		List<CategoryBasicResponse> dtos = categoryMapper.categoryEntityList2DTOList(entities);
		
		return dtos;
	}

	@Override
	public void delete(Long id) {
		if(categoryRepository.findById(id).isEmpty()) {
			throw new ParamNotFound("Id not found");
		}
		categoryRepository.deleteById(id);	
	}

	
}
