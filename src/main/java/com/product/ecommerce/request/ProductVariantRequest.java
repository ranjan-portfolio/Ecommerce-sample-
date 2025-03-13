package com.product.ecommerce.request;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductVariantRequest {
    private String productVariantType;
    private String productVariantValue;
    @Positive
    private Float productPrice;
    @Positive
    private String productDiscount;
    @Positive
    private Long productStock;
    @Positive
    private Long productThreshold;
}
