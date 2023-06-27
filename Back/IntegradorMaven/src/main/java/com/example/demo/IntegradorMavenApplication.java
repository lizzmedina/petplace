package com.example.demo;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class IntegradorMavenApplication {

    @Value("${petplace.version}")
    private String version;

    public static void main(String[] args) {
        SpringApplication.run(IntegradorMavenApplication.class, args);
    }

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Pet Place API")
                        .version(version)
                        .description( "Pet Place - API Swagger documentation")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
