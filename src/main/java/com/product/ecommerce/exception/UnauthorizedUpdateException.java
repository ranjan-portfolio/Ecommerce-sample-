package com.product.ecommerce.exception;

public class UnauthorizedUpdateException  extends Exception{

    public UnauthorizedUpdateException(String msg){
        super(msg);
    }

    public UnauthorizedUpdateException(String msg,Throwable th){
        super(msg,th);
    }
    
}
