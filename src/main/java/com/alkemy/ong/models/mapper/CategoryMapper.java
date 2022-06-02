package com.alkemy.ong.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.models.response.CategoryResponse;

@Component
public class CategoryMapper {

	public List<CategoryBasicResponse> categoryEntityList2DTOList(List<CategoryEntity> entities){
		List<CategoryBasicResponse> dtos = new ArrayList<>();
		
		for (CategoryEntity categoryEntity : entities) {
			CategoryBasicResponse basicDto = new CategoryBasicResponse();
			basicDto.setName(categoryEntity.getName());
			dtos.add(basicDto);
			
		}
		return dtos;
		
	}
	
	public CategoryEntity categoryDTO2Entity(CategoryRequest dto) {
		CategoryEntity entity = new CategoryEntity();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImage(dto.getImage());
		return entity;
	}
	
	public CategoryResponse categoryEntity2DTO(CategoryEntity entity) {
		return CategoryResponse.builder()
				.name(entity.getName())
				.description(entity.getDescription())
				.image(entity.getImage())
				.timestamp(entity.getTimestamp())
				.build();
	}
	
	public void categoryEntityRefreshValues(CategoryEntity entity, CategoryRequest dto) {		
			entity.setName(dto.getName());		
			entity.setDescription(dto.getDescription());
			entity.setImage(dto.getImage());
	}
}
