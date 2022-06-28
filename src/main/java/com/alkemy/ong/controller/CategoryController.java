package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.alkemy.ong.auth.config.SwaggerConfig;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.models.response.PageCategoryResponse;
import com.alkemy.ong.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.CATEGORY_CONTROLLER})
public class CategoryController {

    private static final String CATEGORIES_PATH = "/categories";
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "Get all categories", notes = "Return all categories")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Returns all categories")
    })
    public ResponseEntity<PageCategoryResponse> getAll(
            @RequestParam(value = "page")
            @ApiParam(
                    type = "Integer",
                    value = "page requested from the list of categories (starting in 1)",
                    example = "1",
                    required = true
            ) int offset,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        PageCategoryResponse response = categoryService.getCategories(offset, uriComponentsBuilder.path(CATEGORIES_PATH));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Categories By ID", notes = "Returns all details of category by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Return the requested category"),
        @ApiResponse(code = 404, message = "No category with requested ID is found"),})
    public ResponseEntity<CategoryResponse> getDetail(
            @PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the category requested",
                    example = "1",
                    required = true
            ) Long id
    ) {
        CategoryResponse response = categoryService.getCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @ApiOperation(value = "Create Category", notes = "Allows admin to insert category")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Category created!")
    })
    public ResponseEntity<CategoryResponse> save(
            
            @RequestBody
            @ApiParam(
                    name = "New Category",
                    value = "Category to save",
                    required = true) @Valid CategoryRequest categoryRequest
    ) {
        CategoryResponse response = categoryService.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Category By ID", notes = "Allows admin to update an existing category by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Category updated!"),
        @ApiResponse(code = 404, message = "No category with requested ID is found"),})
    public ResponseEntity<CategoryResponse> update(
            @PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the category requested",
                    example = "1",
                    required = true) Long id,
            @Valid
            @RequestBody
            @ApiParam(
                    name = "New Categpry",
                    value = "Category to save",
                    required = true) CategoryRequest categoryRequest
    ) {
        CategoryResponse response = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Soft Delete Category By ID", notes = "Allows admin to delete category by ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Category soft deleted!"),
        @ApiResponse(code = 404, message = "No category with requested ID is found"),})
    public ResponseEntity<Void> deleteCategory(
            @PathVariable
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the category requested",
                    example = "1",
                    required = true) Long id
    ) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
