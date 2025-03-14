package com.product.ecommerce.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.product.ecommerce.controller.RatingController;
import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.exception.RatingNotFoundException;
import com.product.ecommerce.request.RatingRequest;
import com.product.ecommerce.response.RatingResponse;

public class RatingControllerImpl implements RatingController {

    @Override
    public RatingResponse addRating(RatingRequest ratingRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRating'");
    }

    @Override
    public RatingResponse updateRating(RatingRequest ratingRequest)
            throws ProductNotFoundException, UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRating'");
    }

    @Override
    public ResponseEntity<String> deleteRating(Long ratingId) throws RatingNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRating'");
    }

    @Override
    public RatingResponse getRatingsFortheProduct(Long productId)
            throws ProductNotFoundException, RatingNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRatingsFortheProduct'");
    }

    @Override
    public RatingResponse getRatingDetailsFortheProductByUser(Long productId, String username)
            throws ProductNotFoundException, RatingNotFoundException, UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRatingDetailsFortheProductByUser'");
    }
    
}
