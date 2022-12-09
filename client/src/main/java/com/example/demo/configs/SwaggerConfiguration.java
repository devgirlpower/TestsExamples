package com.example.demo.configs;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration<endpointMediaTypes> {
    @Bean
    public Docket clinicAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(infosApi());
    }
//montei em dois formatos que aprendi, para ver se funciona, mas não...
    private ApiInfo infosApi2() {
//michelle exemplo
        ApiInfo apiInfo = new ApiInfo(
                "Clinic - API Documentation ",
                "API para gerenciamente de clínicas.",
                "1.0",
                "Terms of Service",
                new Contact("Jade Santos", "https://www.estagiaria.com",
                        "jade@email.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );


        return apiInfo;
    }

    //panela dev exemplo
    private ApiInfo infosApi() {
        return new ApiInfoBuilder()
                .title("Spring API - Clinic")
                .description("API para gerenciamento de clínicas.")
                .version("1.0.0")
                .contact(new Contact("Jade Santos", "http://github.com/devgirlpower", "jade@email"))
                .build();
    }



}
