package com.product.ecommerce.entity;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Basket {
    
    private Long basketId;
    private String username;
    private Long productId;
    private Long quantity;
    private Timestamp addedAt;
    private Timestamp updatedAt;
}
