package com.product.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.entity.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long>{
    
}
