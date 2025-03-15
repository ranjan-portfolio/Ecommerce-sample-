package com.product.ecommerce.response;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketResponse {

    private String username;
    private Long productId;
    private Long quantity;
    private Float priceAtTime;
}
