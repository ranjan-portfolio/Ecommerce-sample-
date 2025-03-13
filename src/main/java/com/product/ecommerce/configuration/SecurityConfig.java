package com.product.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf((c)->c.disable())
                   .authorizeHttpRequests((c)->
                                c.requestMatchers("/swagger-ui/index.html","/swagger-ui/**","/v3/api-docs/**").permitAll()
                                .requestMatchers("/category/**").authenticated())
                    .formLogin((c) -> c.disable())
                    .httpBasic(Customizer.withDefaults())
                    .build();
    }

    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
