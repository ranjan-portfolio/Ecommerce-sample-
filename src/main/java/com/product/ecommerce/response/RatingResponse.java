package com.product.ecommerce.response;


import java.time.LocalDate;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingResponse {
    
    private Long ratingId;
    private Long productId;
    private String ratingDescription;
    private float ratingValue;
    private String username;
    private LocalDate ratingDate;
}
