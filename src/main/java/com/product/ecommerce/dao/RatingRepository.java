package com.product.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.ecommerce.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    
}
