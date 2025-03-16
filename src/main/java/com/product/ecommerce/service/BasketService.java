package com.product.ecommerce.service;

import java.util.List;

import com.product.ecommerce.response.BasketResponse;

public interface BasketService {

    public List<BasketResponse> getBasketDetails(String username) ;

    public BasketResponse insertIntoBasket(String username,Long productId,Long quantity,Float price);

    public BasketResponse updateBasket(String username, Long productId,Long quantity);

    public boolean deleteBasket(String username, Long productId);
    
}
