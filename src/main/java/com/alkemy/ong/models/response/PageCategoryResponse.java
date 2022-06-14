package com.alkemy.ong.models.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PageCategoryResponse {
	List<CategoryBasicResponse> categories; 
	private String urlPrevPage;
	private String urlNextPage;
}
