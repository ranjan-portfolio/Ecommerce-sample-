package com.product.ecommerce.service.impl;

import java.util.List;

import com.product.ecommerce.response.BasketResponse;
import com.product.ecommerce.service.BasketService;

public class BasketServiceImpl implements BasketService{

    @Override
    public List<BasketResponse> getBasketDetails(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBasketDetails'");
    }

    @Override
    public List<BasketResponse> insertIntoBasket(String username, Long productId, Long quantity, Float price) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertIntoBasket'");
    }

    @Override
    public BasketResponse updateBasket(String username, Long productId, Long quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBasket'");
    }

    @Override
    public BasketResponse deleteBasket(String username, Long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBasket'");
    }
    
}
