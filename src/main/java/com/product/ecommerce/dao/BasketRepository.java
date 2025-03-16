package com.product.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.entity.Basket;
import java.util.List;


@Repository
public interface BasketRepository extends JpaRepository<Basket,Long>{

    public List<Basket> findByUsername(String username);

    public List<Basket> findByUsernameAndProductId(String username, Long productId);

    public void deleteByUsernameAndProductId(String username,Long productId);
    
}
