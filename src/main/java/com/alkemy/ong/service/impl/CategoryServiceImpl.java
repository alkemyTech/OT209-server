package com.alkemy.ong.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.mapper.CategoryMapper;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.models.response.PageCategoryResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.utility.PaginationHelper;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private PaginationHelper paginationHelper;
	
	@Override
	public PageCategoryResponse getCategories(int offset, UriComponentsBuilder uriComponentsBuilder, String path) {
		int offsetBase10 = offset + 1;
		String uriNextPage = "";
		String uriPrevPage = "";
		
		Page<CategoryEntity> dataPage = categoryRepository.findAll(PageRequest.of(offset, 10));
		
		Map<String, String> UriInfo = paginationHelper.generarpaginadorespuesta(uriComponentsBuilder, path, uriNextPage, uriPrevPage, dataPage, offsetBase10);
		
		uriNextPage = paginationHelper.generarpaginadorespuesta(uriComponentsBuilder, path, uriNextPage, uriPrevPage, dataPage, offsetBase10).get("next");
		uriPrevPage = paginationHelper.generarpaginadorespuesta(uriComponentsBuilder, path, uriNextPage, uriPrevPage, dataPage, offsetBase10).get("prev");
//		uriComponentsBuilder.path(path);
//		if(dataPage.hasNext()) {
//			uriNextPage = uriComponentsBuilder.replaceQueryParam("page", offsetBase10 + 1).build().encode().toUriString();			
//		}
//		if(dataPage.hasPrevious()) {
//			uriPrevPage = uriComponentsBuilder.replaceQueryParam("page", offsetBase10 - 1).build().encode().toUriString();			
//		}
		
		List<CategoryBasicResponse> dtos = categoryMapper.categoryEntityList2DTOList(dataPage.getContent());
		
		return new PageCategoryResponse(dtos, uriPrevPage, uriNextPage);
	}


	@Override
	public CategoryResponse saveCategory(CategoryRequest category) {
		CategoryEntity entity = categoryMapper.categoryDTO2Entity(category);
		categoryRepository.save(entity);
		
		return categoryMapper.categoryEntity2DTO(entity);
	}

	@Override
	public CategoryResponse updateCategory(Long id, CategoryRequest category) {
		Optional<CategoryEntity> entity = categoryRepository.findById(id);
		if(!entity.isPresent()) {
			throw new ParamNotFound("Id not found");
		}
		categoryMapper.categoryEntityRefreshValues(entity.get(), category);
		CategoryEntity entityUpdated = categoryRepository.save(entity.get());
		return categoryMapper.categoryEntity2DTO(entityUpdated);
	}
	
	@Override
	public void delete(Long id) {
		if(categoryRepository.findById(id).isEmpty()) {
			throw new ParamNotFound("Id not found");
		}
		categoryRepository.deleteById(id);	
	}


	@Override
	public CategoryResponse getCategory(Long id) {
		Optional<CategoryEntity> entity = categoryRepository.findById(id);
		if(!entity.isPresent()) {
			throw new ParamNotFound(String.format("Id %s not found in category", id));
		}
		return categoryMapper.categoryEntity2DTO(entity.get());
	}
	

	
}
