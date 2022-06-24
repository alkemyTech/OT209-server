package com.alkemy.ong.controller;

import com.alkemy.ong.auth.config.SwaggerConfig;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.*;
import com.alkemy.ong.service.NewsService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@CrossOrigin("*")
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.NEWS_CONTROLLER})
public class NewsController {

    private static final String NEWS_PATH = "/news";

    @Autowired
    private NewsService newsService;

    @GetMapping
    @ApiOperation(value = "Get All News", notes = "Returns all news")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Returns all news")
    })
    public ResponseEntity<PageNewsResponse> getAll(@RequestParam(value = "page")
            @ApiParam(
                    type = "Integer",
                    value = "Page requested from the list of news (starting in 1)",
                    example = "1",
                    required = true) int offset,
            UriComponentsBuilder uriComponentsBuilder) {

        PageNewsResponse response = newsService.getAll(offset, uriComponentsBuilder.path(NEWS_PATH));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get News By ID", notes = "Returns all details of news by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Return the requested news"),
        @ApiResponse(code = 404, message = "No news with requested ID is found"),})
    public ResponseEntity<NewsResponse> read(@PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the news requested",
                    example = "1",
                    required = true) Long id) {
        if (newsService.itExists(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(newsService.getById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    @ApiOperation(value = "Create News", notes = "Allows user to insert news")
    @ApiResponses({
        @ApiResponse(code = 201, message = "News created!")
    })
    public ResponseEntity<NewsResponse> create(@Valid @RequestBody
            @ApiParam(
                    name = "New News",
                    value = "News to save",
                    required = true) NewsRequest newsRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.create(newsRequest));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update News By ID", notes = "Allows user to update an existing news by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "News updated!"),
        @ApiResponse(code = 404, message = "No news with requested ID is found"),})
    public ResponseEntity<NewsResponse> update(@PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the news requested",
                    example = "1",
                    required = true) Long id,
                                               @Valid @RequestBody
            @ApiParam(
                    name = "New News",
                    value = "News to save",
                    required = true) NewsRequest newsRequest) {
        if (newsService.itExists(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(newsService.updateById(id, newsRequest));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Soft Delete News By ID", notes = "Allows user to delete news by ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "News soft deleted!"),
        @ApiResponse(code = 404, message = "No news with requested ID is found"),})
    public ResponseEntity<Void> delete(@PathVariable @ApiParam(
            name = "id",
            type = "Long",
            value = "ID of the news requested",
            example = "1",
            required = true) Long id) {
        if (newsService.itExists(id)) {
            newsService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
