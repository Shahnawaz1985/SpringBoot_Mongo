package com.eric.spring.boot.env.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConfiguration extends AbstractMongoClientConfiguration  {
	
	private final Environment environment;

    public MongoDBConfiguration(Environment environment) {
        this.environment = environment;
    }
    
    @Bean
    //@DependsOn("embeddedMongoServer")
    public MongoClient mongoClient() {
        //int port = environment.getProperty("local.mongo.port", Integer.class);
    	//int port = environment.getProperty("spring.data.mongodb.port", Integer.class);
        //String host = environment.getProperty("spring.data.mongodb.host", String.class);
        //String database = environment.getProperty("spring.data.mongodb.database", String.class);
        //return MongoClients.create(String.format("mongodb://%s:%d/%s", host, port, database));spring.data.mongodb.uri
    	int port = environment.getProperty("spring.data.mongodb.port", Integer.class);
        String host = environment.getProperty("spring.data.mongodb.host", String.class);
        String database = environment.getProperty("spring.data.mongodb.database", String.class);
        return MongoClients.create(String.format("mongodb://%s:%d/%s", host, port, database));
		/*
		 * final ConnectionString connString = new
		 * ConnectionString(environment.getProperty("spring.data.mongodb.uri")); return
		 * MongoClients.create(connString);
		 */
    }

    protected String getDatabaseName() {
        return "item-collection";
    }
    
    protected String getMappingBasePackage() {
        return "com.eric.spring.boot.collections";
    }
    
    
	/*
	 * @Bean public MongoDatabaseFactory mongoDatabaseFactory() { return new
	 * SimpleMongoClientDatabaseFactory(mongoClient(), "item-collection"); }
	 */
    @Bean
    public MongoTemplate mongoTemplate() {
    	return new MongoTemplate(mongoClient(), "item-collection");
    } 

}
