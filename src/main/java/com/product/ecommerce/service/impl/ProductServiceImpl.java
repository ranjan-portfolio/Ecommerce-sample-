package com.product.ecommerce.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.product.ecommerce.dao.ProductRepository;
import com.product.ecommerce.entity.Product;
import com.product.ecommerce.entity.ProductVariant;
import com.product.ecommerce.exception.ProductNotFoundException;
import com.product.ecommerce.request.ProductRequest;
import com.product.ecommerce.request.ProductVariantRequest;
import com.product.ecommerce.response.ProductResponse;
import com.product.ecommerce.response.ProductVariantResponse;
import com.product.ecommerce.service.ProductService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private static final String PRODUCT_PREFIX_KEY="Product:";

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        
        Product product=transformProductRequestToEntity(productRequest,null);
        Product savedProduct=productRepository.save(product);
        ProductResponse response=transformEntityToProductResponse(savedProduct);
        redisTemplate.opsForValue().set(PRODUCT_PREFIX_KEY+response.getProductId(), response);
        return response;
    }

    @Override
    public List<ProductResponse> createProductInBulk(File file) {
        
        throw new UnsupportedOperationException("Unimplemented method 'createProductInBulk'");
    }

    @Override
    public ProductResponse searchProductById(Long id) throws ProductNotFoundException {
         
        ProductResponse response=getProductDetailsFromCache(id);
         
         if(response!=null){
            return response;
         }
        
         response=productRepository.findById(id)
                .map( this::transformEntityToProductResponse)
                .orElseThrow( ()-> new ProductNotFoundException("Product not found with id:"+id));
            
         redisTemplate.opsForValue().set(PRODUCT_PREFIX_KEY+response.getProductId(),response);

        return response;
    }

    @Override
    public List<ProductResponse> searchProductsByName(String name) throws ProductNotFoundException {
        List<Product> products=productRepository.findProductbyName(name);
        if(products.isEmpty()) throw new ProductNotFoundException("No product found with name:"+ name);
        else{
            List<ProductResponse> productResponseList=products.stream()
            .map(this::transformEntityToProductResponse)
            .collect(Collectors.toList());

            return productResponseList;
        }         
    }

    @Override
    public List<ProductResponse> searchProductsByCategory(String categoryName,Long categoryId) {
        
        List<ProductResponse> responseList=null;
        List<ProductResponse> categoryNameList=null;
        List<ProductResponse> categoryIdList=null;

        if(categoryName!=null && !categoryName.isEmpty()){
            categoryNameList=productRepository.findProductbyCategoryName(categoryName)
            .stream()
            .map(this::transformEntityToProductResponse)
            .collect(Collectors.toList());
        }
        if(categoryId!=null){
            categoryIdList=productRepository.findProductbyCategoryId(categoryId)
                        .stream()
                        .map(this::transformEntityToProductResponse)
                        .collect(Collectors.toList());
        }

        if((categoryNameList==null|| categoryNameList.isEmpty())
                && (categoryIdList==null|| categoryIdList.isEmpty())
        ) return Collections.emptyList();

        responseList=Stream.concat(
            categoryNameList!=null?categoryNameList.stream():Stream.empty(), 
            categoryIdList!=null? categoryIdList.stream():Stream.empty())
            .collect(Collectors.toList());

        return responseList;
    }

    @Override
    public List<ProductResponse> searchProductsByPriceRange(String productName, BigDecimal minPrice,
            BigDecimal maxPrice) {

                List<Product> entityList=productRepository.findProductByPriceRange(productName,new Float(minPrice.doubleValue()),new Float(maxPrice.doubleValue()));
                List<ProductResponse> productResponses=entityList.stream().map( 
                    this::transformEntityToProductResponse)
                    .collect(Collectors.toList());
                return productResponses;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws ProductNotFoundException{
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()) throw new ProductNotFoundException("Product is not found for the id:"+id);
        else{
           Product updatedProduct= transformProductRequestToEntity(productRequest,product.get());
           productRepository.save(updatedProduct);
        }

        return null;
    }

    @Override
    public void deleteProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()) throw new ProductNotFoundException("Product is not found for the id:"+id);
        else{
           productRepository.delete(product.get());
        }

        
    }

    private Product transformProductRequestToEntity(ProductRequest request,Product product){
            if(request==null){
                //throw exception
            }
            
            if(product==null){
                product=new Product();
            }
            
            product.setProductName(request.getProductName());
            product.setProductDescription(request.getProductDescription());
            product.setProductBrand(request.getProductBrand());
            product.setProductURL(request.getProductURL());
            com.product.ecommerce.entity.Category category=new com.product.ecommerce.entity.Category();
            category.setId(request.getCategoryId());
            product.setCategory(category);

            List<ProductVariantRequest> productVariantRequestList=request.getProductVariant();

            

            List<ProductVariant> productVariants=productVariantRequestList.stream().map( (productVariantRequest) ->{
                ProductVariant productVariant=new ProductVariant();

                productVariant.setProductDiscount(productVariantRequest.getProductDiscount());
                productVariant.setProductPrice(productVariantRequest.getProductPrice());
                productVariant.setProductStock(productVariantRequest.getProductStock());
                productVariant.setProductVariantType(productVariantRequest.getProductVariantType());
                productVariant.setProductVariantValue(productVariantRequest.getProductVariantValue());
                productVariant.setProductThreshold(productVariantRequest.getProductThreshold());
                return productVariant;
            }).collect(Collectors.toList());

            product.setProductVariant(productVariants);
            

            return product;
    }

    private ProductResponse transformEntityToProductResponse(Product product){

            if(product==null){
                //throw exception
            }

            ProductResponse response=new ProductResponse();
            response.setCategoryId(product.getCategory().getId());
            response.setProductBrand(product.getProductBrand());
            response.setProductDescription(product.getProductDescription());
            response.setProductId(product.getProductId());
            response.setProductName(product.getProductName());
            response.setProductURL(product.getProductURL());
            List<ProductVariant> productVariants=product.getProductVariant();
            List<ProductVariantResponse> productVariantList=productVariants.stream().map( (productVariant) ->{
                ProductVariantResponse productVariantResponse=new ProductVariantResponse();
                productVariantResponse.setProductDiscount(productVariant.getProductDiscount());
                productVariantResponse.setProductPrice(productVariant.getProductPrice());
                productVariantResponse.setProductStock(productVariant.getProductStock());
                productVariantResponse.setProductThreshold(productVariant.getProductThreshold());
                productVariantResponse.setProductVariantId(productVariant.getProductVariantId());
                productVariantResponse.setProductVariantType(productVariant.getProductVariantType());
                productVariantResponse.setProductVariantValue(productVariant.getProductVariantValue());
                return productVariantResponse;
            }).collect(Collectors.toList());
           
            
            response.setProductVariantResponse(productVariantList);

            return response;


    }

    public ProductResponse getProductDetailsFromCache(Long id){
        
        return (ProductResponse) redisTemplate.opsForValue().get(PRODUCT_PREFIX_KEY+id);
    }
    
}
