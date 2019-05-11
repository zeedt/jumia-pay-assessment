package com.jumia.pay.assessment.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.jumia.pay.assessment.activemq")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.db.host:127.0.0.1}")
    private String mongoHost;

    @Value("${mongo.db.port:27017}")
    private Integer mongoPort;

    @Value("${mongo.db.collection:jumiaPay}")
    private String mongoCollection;
  
    @Override
    protected String getDatabaseName() {
        return mongoCollection;
    }
  
    @Override
    public MongoClient mongoClient() {
        return new MongoClient(mongoHost, mongoPort);
    }
  
    @Override
    protected String getMappingBasePackage() {
        return "com.jumia.pay.assessment.entity";
    }
}
