package com.product.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.ecommerce.dao.CustomerRepository;
import com.product.ecommerce.entity.Customer;
import com.product.ecommerce.exception.CustomerNotFound;
import com.product.ecommerce.exception.UnauthorizedUpdateException;
import com.product.ecommerce.request.CustomerRequest;
import com.product.ecommerce.response.CustomerResponse;
import com.product.ecommerce.service.CustomerService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        String encodedPassword=passwordEncoder.encode(customerRequest.getPassword());
        Customer  newCustomer=new Customer();
        newCustomer.setPassword(encodedPassword);
        newCustomer.setEmail(customerRequest.getEmail());
        newCustomer.setEnabled(true);
        newCustomer.setFirstName(customerRequest.getFirstname());
        newCustomer.setLastName(customerRequest.getLastname());
        newCustomer.setPhoneNumber(customerRequest.getPhone_number());
        newCustomer.setRole("ROLE_USER");
        Customer savedCustomer=customerRepository.save(newCustomer);
        return convertCustomerEntityToResponse(savedCustomer);
    }

    @Override
    public CustomerResponse createAdmin(CustomerRequest customerRequest) {
        String encodedPassword=passwordEncoder.encode(customerRequest.getPassword());
        Customer  newCustomer=new Customer();
        newCustomer.setPassword(encodedPassword);
        newCustomer.setEmail(customerRequest.getEmail());
        newCustomer.setEnabled(true);
        newCustomer.setFirstName(customerRequest.getFirstname());
        newCustomer.setLastName(customerRequest.getLastname());
        newCustomer.setPhoneNumber(customerRequest.getPhone_number());
        newCustomer.setRole("ROLE_ADMIN");
        Customer savedCustomer=customerRepository.save(newCustomer);
        return convertCustomerEntityToResponse(savedCustomer);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest,String username) throws CustomerNotFound,UnauthorizedUpdateException {
        Customer customer=customerRepository.findByUsername(username);
        if(customer==null) throw new CustomerNotFound("Customer with supplied username::"+username+"not found");
        if(!customer.getRole().equals("ROLE_USER")) throw new UnauthorizedUpdateException("Not authorized action");
        if(customerRequest.getUsername()!=null && !customerRequest.getUsername().equals(username)) throw new UnauthorizedUpdateException("username update is not allowed");
        if(customerRequest.getPassword()!=null) throw new UnauthorizedUpdateException("password update is not allowed using this API,use forget_password API");
        if(customerRequest!=null){
            if(customerRequest.getEmail()!=null){
                customer.setEmail(customerRequest.getEmail());
            }
            if(customerRequest.getFirstname()!=null){
                customer.setFirstName(customerRequest.getFirstname());
            }
            if(customerRequest.getLastname()!=null){
                customer.setLastName(customerRequest.getLastname());
            }
            if(customerRequest.getPhone_number()!=null){
                customer.setPhoneNumber(customerRequest.getPhone_number());
            }
        }
        return convertCustomerEntityToResponse(customer);
    }

    @Override
    public CustomerResponse updateAdmin(CustomerRequest customerRequest, String username) throws CustomerNotFound,UnauthorizedUpdateException {
        Customer customer=customerRepository.findByUsername(username);
        if(customer==null) throw new CustomerNotFound("Customer with supplied username::"+username+"not found");
        if(!customer.getRole().equals("ROLE_ADMIN")) throw new UnauthorizedUpdateException("Not authorized action");
        if(customerRequest.getUsername()!=null && !customerRequest.getUsername().equals(username)) throw new UnauthorizedUpdateException("username update is not allowed");
        if(customerRequest.getPassword()!=null) throw new UnauthorizedUpdateException("password update is not allowed using this API,Please connect system administrator");
        if(customerRequest!=null){
            if(customerRequest.getEmail()!=null){
                customer.setEmail(customerRequest.getEmail());
            }
            if(customerRequest.getFirstname()!=null){
                customer.setFirstName(customerRequest.getFirstname());
            }
            if(customerRequest.getLastname()!=null){
                customer.setLastName(customerRequest.getLastname());
            }
            if(customerRequest.getPhone_number()!=null){
                customer.setPhoneNumber(customerRequest.getPhone_number());
            }
        }
        return convertCustomerEntityToResponse(customer);
    }

    @Override
    public boolean deleteCustomer(String username) throws CustomerNotFound {
        customerRepository.deleteById(username);
        return true;
    }

    @Override
    public CustomerResponse getUserDetails(String username) throws CustomerNotFound{
        Customer customer=customerRepository.findByUsername(username);
        if(customer==null) throw new CustomerNotFound("customer not found with supplied username::"+username);
        return convertCustomerEntityToResponse(customer);
    }

    private CustomerResponse convertCustomerEntityToResponse(Customer customer){
        CustomerResponse customerResponse=new CustomerResponse();
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setEnabled(customer.isEnabled());
        customerResponse.setFirstname(customer.getFirstName());
        customerResponse.setLastname(customer.getLastName());
        customerResponse.setPhone_number(customer.getPhoneNumber());
        customerResponse.setRole(customer.getRole());
        customerResponse.setUsername(customer.getUsername());
        return customerResponse;
    }
    
}
