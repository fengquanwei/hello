package com.fengquanwei.hello.spring.data.mongo;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Config
 *
 * @author fengquanwei
 * @create 2018/6/19 18:25
 **/
@Configuration
@EnableMongoRepositories
public class Config {
    @Bean
    public MongoFactoryBean mongo() {
        MongoFactoryBean mongoFactoryBean = new MongoFactoryBean();
        mongoFactoryBean.setHost("127.0.0.1");
        mongoFactoryBean.setPort(27017);

        return mongoFactoryBean;
    }

    @Bean
    public MongoOperations mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, "test");
    }
}
