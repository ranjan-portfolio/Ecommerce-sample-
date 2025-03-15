package com.product.ecommerce.controller.impl;

import java.util.List;

import com.product.ecommerce.controller.BasketController;
import com.product.ecommerce.response.BasketResponse;

public class BasketControllerImpl implements BasketController{

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
