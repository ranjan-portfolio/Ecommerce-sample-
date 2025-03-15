package com.product.ecommerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.exception.RatingNotFoundException;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.RatingRequest;
import com.product.ecommerce.response.RatingResponse;

public interface RatingService {

    public RatingResponse addRating(RatingRequest ratingRequest);

    public RatingResponse updateRating (Long ratingId,RatingRequest request) throws RatingNotFoundException,UnauthorizedUpdateException;

    public boolean deleteRating(Long ratingId) throws RatingNotFoundException;

    public List<RatingResponse> getRatingsFortheProduct(Long productId) throws RatingNotFoundException;

    public List<RatingResponse> getRatingDetailsFortheProductByUser(Long productId, String username) throws RatingNotFoundException;
    
    
}
