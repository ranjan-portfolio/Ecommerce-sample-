package com.product.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productVariantId;
    private String productVariantType;
    private String productVariantValue;
    private Float productPrice;
    private String productDiscount;
    private Long productStock;
    private Long productThreshold;
    @ManyToOne
    @JoinColumn(name = "product_Id",nullable = false)
    private Product product;


    
}
