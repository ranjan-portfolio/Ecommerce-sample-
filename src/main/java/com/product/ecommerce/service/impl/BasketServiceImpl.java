package com.product.ecommerce.service.impl;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.ecommerce.dao.BasketRepository;
import com.product.ecommerce.entity.Basket;
import com.product.ecommerce.response.BasketResponse;
import com.product.ecommerce.response.OrderResponse;
import com.product.ecommerce.service.BasketService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class BasketServiceImpl implements BasketService{

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public List<BasketResponse> getBasketDetails(String username) {
       List<BasketResponse> basketResponses= basketRepository.findByUsername(username)
                        .stream()
                        .map(this::convBasketResponseFromEntity)
                        .collect(Collectors.toList());

        return basketResponses;
    }

    @Override
    public BasketResponse insertIntoBasket(String username, Long productId, Long quantity, Float price) {
        Basket basket=new Basket();
        basket.setUsername(username);
        basket.setPrice(price);
        basket.setProductId(productId);
        basket.setQuantity(quantity);
        Basket newBasketItem=basketRepository.save(basket);
        return convBasketResponseFromEntity(newBasketItem);
    }

    @Override
    public BasketResponse updateBasket(String username, Long productId, Long quantity) {
        Basket basket=basketRepository.findByUsernameAndProductId(username,productId).get(0);
        if(basket!=null && basket.getQuantity()!=quantity){
            basket.setQuantity(quantity);
        }
        Basket savedBasket=basketRepository.save(basket);
        return convBasketResponseFromEntity(savedBasket);
    }

    @Override
    public boolean deleteBasket(String username, Long productId) {
        basketRepository.deleteByUsernameAndProductId(username, productId);
        return true;
    }

    private BasketResponse convBasketResponseFromEntity(Basket basket){
        BasketResponse basketResponse=new BasketResponse();
        basketResponse.setPriceAtTime(basket.getPrice());
        basketResponse.setProductId(basket.getProductId());
        basketResponse.setQuantity(basket.getQuantity());
        basketResponse.setAddedAt(basket.getAddedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        basketResponse.setUpdatedAt(basket.getUpdatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return basketResponse;
    }

    public OrderResponse placeOrder(List<BasketResponse> orderList){

        // save the basket item into order with order status as pending and payment status is pending
        // delete the saved orders from basket
        // Use stripe service to do payment
        // Update order status as processing if payment successful,
        // In case of failure retry after exponential backoff 3 times ,if still failure mark is pending
        // send the order to vendor.
        // Send the result response to the user

        return null;
    }
    
}
