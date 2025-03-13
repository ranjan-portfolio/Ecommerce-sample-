package com.product.ecommerce.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.productName like %:name%" )
    List<Product> findProductbyName(@Param("name") String name);

    @Query("select p from Product p where p.category.id=:id" )
    List<Product> findProductbyCategoryId(@Param("id") Long id);

    @Query("select p from Product p where p.category.categoryName=:name" )
    List<Product> findProductbyCategoryName(@Param("name") String name);

    @Query("select p from Product p JOIN p.productVariant pv where p.productName=:name and pv.productPrice >= :minPrice and pv.productPrice <= :maxPrice")
    List<Product> findProductByPriceRange(@Param("name") String productName,@Param("minPrice") Float minPrice,@Param("maxPrice") Float maxPrice);

    
} 
