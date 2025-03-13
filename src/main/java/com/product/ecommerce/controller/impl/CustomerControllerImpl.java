package com.product.ecommerce.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.ecommerce.controller.CustomerController;
import com.product.ecommerce.exception.CustomerNotFound;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.CustomerRequest;
import com.product.ecommerce.response.CustomerResponse;
import com.product.ecommerce.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

@RequestMapping("/user")
@Tag(name = "User API",description = "User insert/delete/update")
@Getter
@Setter
public class CustomerControllerImpl implements CustomerController{

    @Autowired
    private CustomerService customerService;
    
    @Operation(summary = "Create user", description="Creates a new customer")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Customer created successfully"),
        @ApiResponse(responseCode = "500",description="Error occured while creating customer")
    })
    @PostMapping("/create")
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        CustomerResponse customer=customerService.createCustomer(customerRequest);
        return customer;
    }

    @Operation(summary = "Create user", description="Creates a new admin")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Customer created successfully"),
        @ApiResponse(responseCode = "500",description= "Error occured while creating admin user")
    })
    @PostMapping("/createAdmin")
    public CustomerResponse createAdmin(CustomerRequest customerRequest) {
        CustomerResponse customerResponse=customerService.createAdmin(customerRequest);
        return customerResponse;
    }

    @Override
    @Operation(summary = "Update user", description="Updates a user details")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Customer updated successfully"),
        @ApiResponse(responseCode = "404",description = "Customer not found"),
        @ApiResponse(responseCode = "500",description= "Error occured while creating user")
    })
    @PutMapping("/updateCustomer")
    public CustomerResponse updateCustomer(CustomerRequest customerRequest,String username) throws CustomerNotFound,UnauthorizedUpdateException{
        CustomerResponse customerResponse=customerService.updateCustomer(customerRequest,username);
        return customerResponse;
    }

    @Override
    @Operation(summary = "Update admin", description="Updates admin details")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Admin details updated successfully"),
        @ApiResponse(responseCode = "404",description = "Admin not found"),
        @ApiResponse(responseCode = "500",description= "Error occured while updating admin")
    })
    @PutMapping("/updateCustomer")
    public CustomerResponse updateAdmin(CustomerRequest customerRequest, String username) throws CustomerNotFound,UnauthorizedUpdateException {
        CustomerResponse customerResponse=customerService.updateAdmin(customerRequest,username);
        return customerResponse;
    }

    @Override
    @Operation(summary = "delete user", description="delete user")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "User details deleted successfully"),
        @ApiResponse(responseCode = "404",description = "User details not found"),
        @ApiResponse(responseCode = "500",description= "Error occured while deleting user")
    })
    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(String username) throws CustomerNotFound{
        customerService.deleteCustomer(username);
    }


    @Override
    @Operation(summary = "get user details", description="Get user details")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "User details found"),
        @ApiResponse(responseCode = "404",description = "User details not found"),
        @ApiResponse(responseCode = "500",description= "Error occured during user search")
    })
    @GetMapping("/getUserDetails")
    public CustomerResponse getUser(String username) throws CustomerNotFound{

        CustomerResponse customerResponse=customerService.getUserDetails(username);
        return customerResponse;
    }

    
}
