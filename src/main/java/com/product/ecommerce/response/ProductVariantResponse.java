package com.product.ecommerce.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantResponse {
    
    private Long productVariantId;
    private String productVariantType;
    private String productVariantValue;
    private Float productPrice;
    private String productDiscount;
    private Long productStock;
    private Long productThreshold;
    
}
