package com.product.ecommerce.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private Long productId;
    private Long quantity;
    private Float priceAtTime;
    private Float subtotal;
    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    private Order order;
    
}
