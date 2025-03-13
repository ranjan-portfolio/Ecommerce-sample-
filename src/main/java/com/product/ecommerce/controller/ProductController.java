package com.product.ecommerce.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.request.ProductRequest;
import com.product.ecommerce.response.ProductResponse;

public interface ProductController {

    public ProductResponse createProduct(ProductRequest productRequest);
    public List<ProductResponse> createProductInBulk(MultipartFile file);
    public ProductResponse searchProductById(Long id) throws ProductNotFoundException;
    public List<ProductResponse> searchProductsByName(String name) throws ProductNotFoundException;
    public List<ProductResponse> searchProductsByCategory(Optional<String> categoryName,Optional<Long> categoryId) throws ProductNotFoundException;
    public List<ProductResponse> searchProductsByPriceRange(String productName,BigDecimal minPrice,BigDecimal maxPrice) throws ProductNotFoundException;
    public ProductResponse updateProduct(Long id,ProductRequest productRequest) throws ProductNotFoundException;
    public ResponseEntity<String> deleteProductById(Long id) throws ProductNotFoundException;


    
}
