package com.product.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
    @NotNull
    private Long productId;
    @NotBlank
    private String ratingDescription;
    @NotNull
    private Float ratingValue;
    @NotBlank
    private String username;
}
