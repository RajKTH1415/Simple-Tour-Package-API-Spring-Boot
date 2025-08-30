package com.backendapi.simpletourpackageapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI tourServiceOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("TOUR PACKAGE MANAGEMENT SYSTEM - REST API")
                        .description("API documentation for managing tour packages including creating, fetching, and updating tours.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Rajkumar prasad")
                                .email("rajkumarprasad@gmail.com")
                                .url("https://github.com/RajKTH1415"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository - Tour Package API")
                        .url("https://github.com/RajKTH1415/Simple-Tour-Package-API-Spring-Boot"));
    }
}
