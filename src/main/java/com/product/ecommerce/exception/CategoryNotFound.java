package com.product.ecommerce.exception;

public class CategoryNotFound extends Exception {

    public CategoryNotFound(String msg){
        super(msg);
    }

    public CategoryNotFound(String msg,Throwable th){
        super(msg,th);
    }

    
    
}
