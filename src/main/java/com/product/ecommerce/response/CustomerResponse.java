package com.product.ecommerce.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private String username;
    private boolean enabled;
    private String firstname;
    private String lastname;
    private String email;
    private String phone_number;
    private String role;

    
}
