package com.product.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {

    
} 
