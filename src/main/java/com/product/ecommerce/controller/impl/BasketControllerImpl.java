package com.product.ecommerce.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.controller.BasketController;
import com.product.ecommerce.response.BasketResponse;
import com.product.ecommerce.service.BasketService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Getter
@Setter
@Slf4j
public class BasketControllerImpl implements BasketController{

    @Autowired
    private BasketService basketService;

    @Override
    public List<BasketResponse> getBasketDetails(String username) {
       return basketService.getBasketDetails(username);
    }

    @Override
    public BasketResponse insertIntoBasket(String username, Long productId, Long quantity, Float price) {
        return basketService.insertIntoBasket(username, productId, quantity, price);
    }

    @Override
    public BasketResponse updateBasket(String username, Long productId, Long quantity) {
        return basketService.updateBasket(username, productId, quantity);
    }

    @Override
    public ResponseEntity<String> deleteBasket(String username, Long productId) {
        basketService.deleteBasket(username, productId);
        return new ResponseEntity<String>("basket item deleted successfully",HttpStatus.ACCEPTED);
    }

    
    
}
