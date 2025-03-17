package com.product.ecommerce.response;

import java.security.Timestamp;
import java.util.List;

import com.product.ecommerce.entity.OrderItem;
import com.product.ecommerce.entity.OrderStatus;
import com.product.ecommerce.entity.PaymentStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderResponse {

    
    private Long orderId;
    private String username;
    private Float totalAmount;
    private String currency;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private String shippingAddress;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<OrderItem> orderItemList;
    
}
