package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {

	    StackEntity stack1 = new StackEntity(1);
	    System.out.println(" stack1 id : " + stack1.getId());

		SpringApplication.run(RestServiceApplication.class, args);
	}

}
