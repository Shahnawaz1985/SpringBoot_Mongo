package com.eric.spring.boot.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.eric.spring.boot.config.mongo"})
public class SpringBootMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoApplication.class, args);
	}

}
