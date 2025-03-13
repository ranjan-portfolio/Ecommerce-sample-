package com.product.ecommerce.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.request.ProductRequest;
import com.product.ecommerce.response.ProductResponse;

public interface ProductService {

    public ProductResponse createProduct(ProductRequest productRequest);

    public List<ProductResponse> createProductInBulk(File file);
    
    public ProductResponse searchProductById(Long id) throws ProductNotFoundException;
    
    public List<ProductResponse> searchProductsByName(String name) throws ProductNotFoundException;
    
    public List<ProductResponse> searchProductsByCategory(String categoryName,Long categoryId) throws ProductNotFoundException;
    
    public List<ProductResponse> searchProductsByPriceRange(String productName,BigDecimal minPrice,BigDecimal maxPrice) throws ProductNotFoundException;
    
    public ProductResponse updateProduct(Long id,ProductRequest productRequest) throws ProductNotFoundException;
    
    public void deleteProductById(Long id) throws ProductNotFoundException;
    
}
