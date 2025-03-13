package com.product.ecommerce.exception;

public class ProductNotFoundException extends Exception{
    
   public ProductNotFoundException(String message){
        super(message);

    }

   public ProductNotFoundException(String message,Throwable th){
        super(message,th);
    }
}
