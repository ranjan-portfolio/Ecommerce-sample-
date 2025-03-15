package com.product.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.entity.Rating;
import com.product.ecommerce.exception.RatingNotFoundException;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    @Query("select from rating where ratng.product.productId=:productId")
    public List<Rating> findRatingsByProductId(Long productId) throws RatingNotFoundException;

    public List<Rating> findRatingByProductIdAndUsernameList(Long productId,String username) throws RatingNotFoundException;
}
