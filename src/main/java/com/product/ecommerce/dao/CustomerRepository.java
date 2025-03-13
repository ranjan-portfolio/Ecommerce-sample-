package com.product.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    public Customer findByUsername(String username);
} 