package com.product.ecommerce.controller.impl;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.ecommerce.controller.ProductController;
import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.request.ProductRequest;
import com.product.ecommerce.response.ProductResponse;
import com.product.ecommerce.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/product")
@Tag(name="Product APIs",description="List all product APIs")
@Getter
@Setter
public class ProductControllerImpl implements ProductController{

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create product",description = "This will create a new product")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "New product created"),
        @ApiResponse(responseCode = "500",description = "Problem encountered while creating new product")
    })
    @PostMapping("/insert")
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @Override
    public List<ProductResponse> createProductInBulk(MultipartFile file) {
        // TODO 
        throw new UnsupportedOperationException("Unimplemented method 'createProductInBulk'");
    }

    @Override
    @GetMapping("/search/{id}")
    @Operation(summary = "search product by product Id",description = "search product by product Id")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Get product by Id"),
        @ApiResponse(responseCode = "404",description = "No product found for the given Id"),
        @ApiResponse(responseCode = "500",description = "Problem encountered while creating new product")
    })
    public ProductResponse searchProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
            return productService.searchProductById(id);
    }

    @Override
    @GetMapping("/search")
    @Operation(summary = "search product by product Id",description = "search product by product Id")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Get product by Id"),
        @ApiResponse(responseCode = "404",description = "No product found for the given Id"),
        @ApiResponse(responseCode = "500",description = "Problem encountered while creating new product")
    })
    public List<ProductResponse> searchProductsByName(@RequestParam("productName") String name) throws ProductNotFoundException {
        return productService.searchProductsByName(name);
    }

    @Override
    @GetMapping("/search_by_category")
    @Operation(summary = "search product by product Id",description = "search product by product Id")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Get product by Id"),
        @ApiResponse(responseCode = "404",description = "No product found for the given Id"),
        @ApiResponse(responseCode = "500",description = "Problem encountered while creating new product")
    })
    public List<ProductResponse> searchProductsByCategory(@RequestParam("categoryName") Optional<String> categoryName
                                                            ,@RequestParam("categoryId")Optional<Long> categoryId)
                                                            throws ProductNotFoundException {

            if(!categoryId.isEmpty() && !categoryName.isPresent()){
                //throw validation error
            }
            return productService.searchProductsByCategory(categoryName.get(),categoryId.get());
    }

    @Override
    @GetMapping("/search_by_price_range")
    @Operation(summary = "search product by price range",description = "search product by price range")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "search product by price range"),
        @ApiResponse(responseCode = "404",description = "No product found for the given product name"),
        @ApiResponse(responseCode = "500",description = "Problem encountered while searching product")
    })
    public List<ProductResponse> searchProductsByPriceRange(String productName, BigDecimal minPrice,
            BigDecimal maxPrice) throws ProductNotFoundException {

            //Needs to add sorting feature
        return productService.searchProductsByPriceRange(productName, minPrice, maxPrice);
    }

    @Override
    @PutMapping("/update/{id}")
    @Operation(summary = "update product",description = "update product")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "product update"),
        @ApiResponse(responseCode = "404",description = "No product found for the given Id"),
        @ApiResponse(responseCode = "500",description = "Problem encountered while updating product")
    })
    public ProductResponse updateProduct(@PathVariable("id")Long id, 
                                         @RequestBody(required = true)ProductRequest productRequest) 
                                         throws ProductNotFoundException {
        return productService.updateProduct(id, productRequest);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete by product Id",description = "delete by product Id")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "product deleted"),
        @ApiResponse(responseCode = "404",description = "No product found for the given Id"),
        @ApiResponse(responseCode = "500",description = "Problem encountered while deleting product")
    })
    public ResponseEntity<String> deleteProductById(@PathVariable("id")Long id) throws ProductNotFoundException {
         productService.deleteProductById(id);
         return new ResponseEntity<String>("product deleted",HttpStatus.OK);
    }
    
}
