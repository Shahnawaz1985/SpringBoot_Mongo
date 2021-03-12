package com.eric.spring.boot.config.mongo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.eric.spring.boot.env.mongo.config", "com.eric.spring.boot.controller", "com.eric.spring.boot.mongo.utils"})
@EntityScan(basePackages = {"com.eric.spring.boot.collections"})
@EnableMongoRepositories(basePackages = {"com.eric.spring.boot.repositories"})
public class MongoConfig {

}
