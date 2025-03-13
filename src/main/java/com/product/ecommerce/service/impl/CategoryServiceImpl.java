package com.product.ecommerce.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.product.ecommerce.dao.CategoryRepository;
import com.product.ecommerce.entity.Category;
import com.product.ecommerce.exception.CategoryNotFound;
import com.product.ecommerce.request.CategoryRequest;
import com.product.ecommerce.service.CategoryService;

import lombok.Getter;

@Service
@Getter
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Cacheable(value="categories")
    public List<com.product.ecommerce.response.Category> searchAllCategory() throws CategoryNotFound {
        
          Iterable<com.product.ecommerce.entity.Category> categories=categoryRepository.findAll();
          if(categories==null){
            throw new CategoryNotFound("No categories found");
          }

          List<com.product.ecommerce.response.Category> categoryList=new ArrayList<>();

          for(Category category: categories){
            try{
                com.product.ecommerce.response.Category categoryResponse=new com.product.ecommerce.response.Category();
                categoryResponse.setCategoryId(category.getId());
                categoryResponse.setCategoryName(category.getCategoryName());
                categoryResponse.setCategoryURL(new URL(category.getCategoryURL()));
                categoryList.add(categoryResponse);
            }catch(Exception ex){
                throw new RuntimeException("Malformed url supplied for::"+category.getId());
            }
                
          }

          return categoryList;
    }

    @Override
    @Cacheable(value="categories",key="#id")
    public com.product.ecommerce.response.Category findCategoryById(Long id) throws CategoryNotFound{
        
        return categoryRepository.findById(id)
        .map( s ->{
            com.product.ecommerce.response.Category categoryResponse=
                new com.product.ecommerce.response.Category();
                try{
                    categoryResponse.setCategoryId(s.getId());
                    categoryResponse.setCategoryName(s.getCategoryName());
                    categoryResponse.setCategoryURL(new URL(s.getCategoryURL()));
                }catch(MalformedURLException ex){
                    throw new RuntimeException("category url for::"+categoryResponse.getCategoryId()+"not correct");
                }
                return categoryResponse;

        }).orElseThrow(() -> new CategoryNotFound("No category found for the supplied Id"));
    }

    @Override
    @CachePut(value="categories",key="#result.categoryId")
    public com.product.ecommerce.response.Category updateCategory(CategoryRequest category) throws CategoryNotFound{
        Category catgorytoUpdate=categoryRepository.findById(category.getCategoryId()).get();
        if(catgorytoUpdate==null){
            throw new CategoryNotFound("category not found for update");
        }
        catgorytoUpdate.setCategoryName(category.getCategoryName());
        catgorytoUpdate.setCategoryURL(category.getCategoryURL().toString());
        Category savedCategory= categoryRepository.save(catgorytoUpdate);

        com.product.ecommerce.response.Category categoryResponse=new com.product.ecommerce.response.Category();
          try{
                categoryResponse.setCategoryId(savedCategory.getId());
                categoryResponse.setCategoryName(savedCategory.getCategoryName());
                categoryResponse.setCategoryURL(new URL(savedCategory.getCategoryURL()));
            }catch(MalformedURLException ex){
                throw new RuntimeException("category url for::"+categoryResponse.getCategoryId()+"not correct");
            }
        return categoryResponse;
    }

    @Override
    @CacheEvict(value="categories",key="#id")
    public void deleteCategory(Long id) {
         categoryRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value="categories",key="'all'")
    public void deleteAllCategory() {
         categoryRepository.deleteAll();
    }

    @Override
    @CachePut(value="categories",key="#result.id")
    public Category insertNewCategory(CategoryRequest category) {
        Category categoryEntity=new Category();
        categoryEntity.setCategoryName(category.getCategoryName());
        categoryEntity.setCategoryURL(category.getCategoryURL().toString());

       Category savedCategory= categoryRepository.save(categoryEntity);
       return savedCategory;
    }
    
}
