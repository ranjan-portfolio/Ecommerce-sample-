package com.product.ecommerce.controller;

import com.product.ecommerce.exception.CustomerNotFound;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.CustomerRequest;
import com.product.ecommerce.response.CustomerResponse;

public interface CustomerController {

    public CustomerResponse createCustomer(CustomerRequest customerRequest);

    public CustomerResponse createAdmin(CustomerRequest customerRequest);

    public CustomerResponse updateCustomer(CustomerRequest customerRequest,String username) throws CustomerNotFound,UnauthorizedUpdateException;

    public CustomerResponse updateAdmin(CustomerRequest customerRequest,String username) throws CustomerNotFound,UnauthorizedUpdateException;

    public void deleteCustomer(String username) throws CustomerNotFound;

    public CustomerResponse getUser(String username) throws CustomerNotFound;
    
}
