package com.product.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.product.ecommerce.exception.RatingNotFoundException;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.RatingRequest;
import com.product.ecommerce.response.RatingResponse;

public interface RatingController {

    public RatingResponse addRating(RatingRequest ratingRequest);

    public RatingResponse updateRating (Long id,RatingRequest ratingRequest) throws RatingNotFoundException,UnauthorizedUpdateException;

    public ResponseEntity<String> deleteRating(Long ratingId) throws RatingNotFoundException;

    public List<RatingResponse> getRatingsFortheProduct(Long productId) throws RatingNotFoundException;

    public List<RatingResponse> getRatingDetailsFortheProductByUser(Long productId, String username) throws RatingNotFoundException;
    
}
