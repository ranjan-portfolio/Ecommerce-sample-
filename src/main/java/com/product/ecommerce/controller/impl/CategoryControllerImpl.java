package com.product.ecommerce.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.controller.CategoryController;
import com.product.ecommerce.exception.CategoryNotFound;
import com.product.ecommerce.request.CategoryRequest;
import com.product.ecommerce.response.Category;
import com.product.ecommerce.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;


@Slf4j
@RestController
@Getter
@Tag(name="Category API",description="Category add/update/delete")
@RequestMapping("/category")
public class CategoryControllerImpl implements CategoryController{

   

    @Autowired
    public CategoryService categoryService;

    
    @GetMapping("/search")
    @Operation(summary = "Find all categories",description = "Fetches all categories configured for the shop")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Category found"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    }
    )
    public List<Category> searchAllCategory() throws CategoryNotFound {
        log.info("Inside searchAllCategory ");
        return categoryService.searchAllCategory();
    }

    @Operation(summary ="Get category by id",description="Returns category by Id")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Categories in the system successfully returned"),
        @ApiResponse(responseCode = "404",description = "No categories found"),
        @ApiResponse(responseCode = "500",description = "System failure encountered")

    })
    @GetMapping("/search/{id}")
    public Category findCategoryById(@PathVariable Long id) throws CategoryNotFound {
        log.info("Inside findCategoryById.. ");
         return categoryService.findCategoryById(id);
    }

    @Operation(summary ="update existing category")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description ="category successfully updated"),
        @ApiResponse(responseCode="404", description ="category not found"),
        @ApiResponse(responseCode="500", description ="System failure encountered")

    })
    @PutMapping("/update/{id}")
    public Category updateCategory(@RequestBody CategoryRequest categoryRequest) {
        log.info("Inside update category ");
        try{
            return categoryService.updateCategory(categoryRequest);
        }catch(CategoryNotFound categoryNotFound){
            
        }
        return null;
         
    }

    @Operation(summary="Delete category by id")
    @ApiResponses(value={
        @ApiResponse(responseCode="200",description="category successfully deleted"),
        @ApiResponse(responseCode="404",description="category not found"),
        @ApiResponse(responseCode="500",description="System failure encountered")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        log.info("Inside delete category ");
         categoryService.deleteCategory(id);
    }


    @Operation(summary="Delete all categories")
    @ApiResponses(value={
        @ApiResponse(responseCode="200",description="category successfully deleted"),
        @ApiResponse(responseCode="404",description="category not found"),
        @ApiResponse(responseCode="500",description="System failure encountered")
    })
    @DeleteMapping("/delete")
    public void deleteAllCategory() {
        log.info("Inside delete category ");
         categoryService.deleteAllCategory();
    }

    @Operation(summary="Add a new category")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "New Category inserted"),
        @ApiResponse(responseCode = "500",description="A error occured while inserting the category")
    })
    @PostMapping("/insert")
    public com.product.ecommerce.entity.Category insertNewCategory(@RequestBody CategoryRequest categoryRequest){
        log.info("Inside insert category ");
        return categoryService.insertNewCategory(categoryRequest);

    }
    
}
