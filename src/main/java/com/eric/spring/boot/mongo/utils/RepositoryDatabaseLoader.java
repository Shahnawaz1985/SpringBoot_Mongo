package com.eric.spring.boot.mongo.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.eric.spring.boot.collections.Items;



/**
 * 
 * @author Shahnawaz
 *
 */
@Component
public class RepositoryDatabaseLoader {
	
	@Bean
	CommandLineRunner initialize(MongoOperations mongo) {
		return args -> {
			mongo.save(new Items("Alf alarm Clock", 25.77));
			mongo.save(new Items("Smurf TV tray", 29.25));
		};
	}

}
