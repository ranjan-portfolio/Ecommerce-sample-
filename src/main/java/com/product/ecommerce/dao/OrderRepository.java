package com.product.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
