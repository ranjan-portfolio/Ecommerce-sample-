package com.product.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.product.ecommerce.dao.RatingRepository;
import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.exception.RatingNotFoundException;
import com.product.ecommerce.request.RatingRequest;
import com.product.ecommerce.response.RatingResponse;
import com.product.ecommerce.service.RatingService;

public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

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
