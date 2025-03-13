package com.product.ecommerce.response;

import java.io.Serializable;
import java.net.URL;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Category implements Serializable {

    private Long categoryId;
    private String categoryName;
    private URL categoryURL;
}
