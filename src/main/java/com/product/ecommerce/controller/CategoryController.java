package com.product.ecommerce.controller;

import java.util.List;

import com.product.ecommerce.exception.CategoryNotFound;
import com.product.ecommerce.request.CategoryRequest;
import com.product.ecommerce.response.Category;

public interface CategoryController {

    public List<Category> searchAllCategory() throws CategoryNotFound;

    public Category findCategoryById(Long id) throws CategoryNotFound;

    public Category updateCategory(CategoryRequest category) throws CategoryNotFound;

    public void deleteCategory(Long id);

    public void deleteAllCategory();

    public com.product.ecommerce.entity.Category insertNewCategory(CategoryRequest category);
    
} 
