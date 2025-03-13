package com.product.ecommerce.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;




@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .description("API for E-Commerce platform")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Ranjanabha Bhattacharya")
                                .url("https://ranjanabha.com")
                                .email("ranjanabha@gmail.com")
                        )

                )
                .externalDocs(new ExternalDocumentation()
                        .description("More API Documentation")
                        .url("https://ranjanabha-docs-url.com"))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("basicAuth", new SecurityScheme()
                    .name("basicAuth")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("basic") // HTTP Basic Authentication
                ));
    }

   
}
