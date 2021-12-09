package com.eric.banking.cqrses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.eric.banking.cqrses"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "CQRS and ES using Spring Boot and Axon",
                "App to demonstrate CQRS & ES Using Spring Boot and Axon",
                "0.0.1-SNAPSHOT",
                "Terms of Service",
                new Contact("Paramswari Bala",
                        "https://blog.eswaribala.com",
                        "eswaribala@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }
}
