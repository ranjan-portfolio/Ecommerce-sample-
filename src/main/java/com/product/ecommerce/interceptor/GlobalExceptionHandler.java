package com.product.ecommerce.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.ecommerce.exception.CategoryNotFound;
import com.product.ecommerce.exception.ProductNotFoundException;

import io.swagger.v3.oas.annotations.Hidden;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<String> handleCategoryNotFOund(CategoryNotFound ex){
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<String>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
       List<FieldError> fieldErrors=  ex.getFieldErrors();
       Map<String,String> fieldErrorMap=new HashMap<String,String>();
       for(FieldError fieldError:fieldErrors){
        fieldErrorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
       }
       return new ResponseEntity<Map<String,String>>(fieldErrorMap, HttpStatus.BAD_REQUEST);
    }
}
