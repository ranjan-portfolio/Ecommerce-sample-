package com.product.ecommerce.exception;

public class RatingNotFoundException extends Exception {
    
    public RatingNotFoundException(String msg){
        super(msg);
    }

    public RatingNotFoundException(String msg,Throwable th){
        super(msg,th);
    }
}
