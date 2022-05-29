package com.alkemy.ong.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.response.CategoryBasicResponse;

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
}
