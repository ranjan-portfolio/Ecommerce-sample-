package com.product.ecommerce.exception;

public class CustomerNotFound extends Exception{

    public CustomerNotFound(String msg){
        super(msg);
    }

    public CustomerNotFound(String msg,Throwable th){
        super(msg,th);
    }
    
}
