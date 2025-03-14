package com.product.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.exception.RatingNotFoundException;
import com.product.ecommerce.request.RatingRequest;
import com.product.ecommerce.response.RatingResponse;

public interface RatingController {

    public RatingResponse addRating(RatingRequest ratingRequest);

    public RatingResponse updateRating (RatingRequest ratingRequest) throws ProductNotFoundException,UsernameNotFoundException;

    public ResponseEntity<String> deleteRating(Long ratingId) throws RatingNotFoundException;

    public RatingResponse getRatingsFortheProduct(Long productId) throws ProductNotFoundException,RatingNotFoundException;

    public RatingResponse getRatingDetailsFortheProductByUser(Long productId, String username) throws ProductNotFoundException,RatingNotFoundException,UsernameNotFoundException;
    
}
