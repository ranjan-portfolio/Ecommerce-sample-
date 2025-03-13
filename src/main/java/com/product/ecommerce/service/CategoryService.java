package com.product.ecommerce.service;
import java.util.List;

import com.product.ecommerce.entity.Category;
import com.product.ecommerce.exception.CategoryNotFound;
import com.product.ecommerce.request.CategoryRequest;

public interface CategoryService {

    public List<com.product.ecommerce.response.Category> searchAllCategory() throws CategoryNotFound;

    public com.product.ecommerce.response.Category findCategoryById(Long id) throws CategoryNotFound;

    public com.product.ecommerce.response.Category updateCategory(CategoryRequest category) throws CategoryNotFound;

    public void deleteCategory(Long id);

    public void deleteAllCategory();

    public Category insertNewCategory(CategoryRequest category);
} 
