package com.product.ecommerce.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;
    private Long categoryId;
    private String productName;
    private String productDescription;
    private String productBrand;
    private String productURL;
    private List<ProductVariantResponse> productVariantResponse;

    
}
