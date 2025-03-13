package com.product.ecommerce.service;
import com.product.ecommerce.exception.CustomerNotFound;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.CustomerRequest;
import com.product.ecommerce.response.CustomerResponse;

public interface CustomerService {
   
    public CustomerResponse createCustomer(CustomerRequest customerRequest);

    public CustomerResponse createAdmin(CustomerRequest customerRequest);

    public CustomerResponse updateCustomer(CustomerRequest customerRequest,String username) throws CustomerNotFound,UnauthorizedUpdateException;

    public CustomerResponse updateAdmin(CustomerRequest customerRequest,String username)throws CustomerNotFound,UnauthorizedUpdateException;

    public boolean deleteCustomer(String username)throws CustomerNotFound;

    public CustomerResponse getUserDetails(String username)throws CustomerNotFound;

    
} 
