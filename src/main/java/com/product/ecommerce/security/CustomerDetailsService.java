package com.product.ecommerce.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.ecommerce.dao.CustomerRepository;
import com.product.ecommerce.entity.Customer;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          Customer customer=customerRepository.findByUsername(username);
          if(customer==null) throw new UsernameNotFoundException("supplied username is not configured");
          return new User(customer.getUsername(), passwordEncoder.encode(customer.getPassword()), List.of(new SimpleGrantedAuthority(customer.getRole())) );
    }
    
}
