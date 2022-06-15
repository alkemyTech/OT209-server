package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.*;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/news")
@CrossOrigin("*")
@RequiredArgsConstructor
public class NewsController {

    private static final String NEWS_PATH = "/news";

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<PageNewsResponse> getAll(
            @RequestParam(value = "page") int offset,
            UriComponentsBuilder uriComponentsBuilder){

        PageNewsResponse response = newsService.getAll(offset, uriComponentsBuilder.path(NEWS_PATH));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDetailsResponse> read(@PathVariable Long id){
        if(newsService.itExists(id)){
            return ResponseEntity.status(HttpStatus.OK).body(newsService.getById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<NewsDetailsResponse> create(@Valid @RequestBody NewsRequest newsRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.create(newsRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDetailsResponse> update (@PathVariable Long id, @Valid @RequestBody NewsRequest newsRequest){
        if(newsService.itExists(id)){
            return ResponseEntity.status(HttpStatus.OK).body(newsService.updateById(id, newsRequest));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(newsService.itExists(id)){
            newsService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
