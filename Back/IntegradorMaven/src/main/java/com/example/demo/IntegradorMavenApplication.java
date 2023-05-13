package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
@Configuration
public class IntegradorMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradorMavenApplication.class, args);
	}

}
