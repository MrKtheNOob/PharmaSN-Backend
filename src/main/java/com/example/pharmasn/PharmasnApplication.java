package com.example.pharmasn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PharmasnApplication {
	public static void main(String[] args) {
		SpringApplication.run(PharmasnApplication.class, args);
	}
	@GetMapping
	public String helloWorld(){
		return "Hello World Spring boot";
	}
}
