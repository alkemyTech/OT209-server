package com.alkemy.ong.models.response;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "Category Response (Page)", description = "Returns a page with 10 categories")
public class PageCategoryResponse {
	@ApiModelProperty(name = "Page",
            value = "Page of news")
	List<CategoryBasicResponse> categories;
	
	@ApiModelProperty(name = "Previous Page",
            value = "Previous Page",
            example = "path/categories?page={prev-page}")
	private String urlPrevPage;
	
	@ApiModelProperty(name = "Next Page",
            value = "Next Page",
            example = "path/categories?page={next-page}")
	private String urlNextPage;
}
