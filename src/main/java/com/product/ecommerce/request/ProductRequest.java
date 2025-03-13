package com.product.ecommerce.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    @NotBlank(message = "product name cannot be blank")
    @Size(min = 1,max=1000, message="product name exceeds 1000 character")
    private String productName;
    @NotNull
    @Positive(message = "category Id needs to be positive")
    private Long categoryId;
    @NotBlank(message = "product name cannot be blank")
    @Size(min = 1,max=65000, message="product name exceeds 65000 character")
    private String productDescription;
    @NotBlank(message = "Product brand cannot be empty")
    @Size(min = 1,max=1000, message="product name exceeds 1000 character")
    private String productBrand;
    @NotBlank
    @Pattern(
        regexp = "^(file://(/[^\s]*)?|https?://[^\s/$.?#].[^\s]*)$", 
        message = "Invalid URL format. It must be a valid file:// or http:// URL."
    )
    private String productURL;
    @NotNull
    private List<ProductVariantRequest> productVariant;
}
