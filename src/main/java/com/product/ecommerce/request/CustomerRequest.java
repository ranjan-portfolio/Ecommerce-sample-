package com.product.ecommerce.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone_number;
}
