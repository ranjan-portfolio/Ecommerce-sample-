package com.product.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.ecommerce.response.BasketResponse;

public interface BasketController {

    public List<BasketResponse> getBasketDetails(String username) ;

    public BasketResponse insertIntoBasket(String username,Long productId,Long quantity,Float price);

    public BasketResponse updateBasket(String username, Long productId,Long quantity);

    public ResponseEntity<String> deleteBasket(String username, Long productId);
}
