package com.alkemy.ong.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "News Response (Page)", description = "Returns a page with 10 news")
public class PageNewsResponse {

    @ApiModelProperty(name = "Page",
            value = "Page of news")
    private List<NewsResponse> news;

    @ApiModelProperty(name = "Previous Page",
            value = "Previous Page",
            example = "path/news?page={prev-page}")
    private String urlPrevPage;

    @ApiModelProperty(name = "Next Page",
            value = "Next Page",
            example = "path/news?page={next-page}")
    private String urlNextPage;

}
