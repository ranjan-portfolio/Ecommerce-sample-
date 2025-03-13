package com.product.ecommerce.request;

import java.io.Serializable;
import java.net.URL;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest implements Serializable{
    private Long categoryId;
    private String categoryName;
    private URL categoryURL;
}
