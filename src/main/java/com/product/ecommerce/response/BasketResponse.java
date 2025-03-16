package com.product.ecommerce.response;



import java.time.LocalDate;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketResponse {

    private String username;
    private Long productId;
    private Long quantity;
    private Float priceAtTime;
    private LocalDate updatedAt;
    private LocalDate addedAt;
}
