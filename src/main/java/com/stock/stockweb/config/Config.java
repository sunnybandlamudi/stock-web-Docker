package com.stock.stockweb.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }



//    @Bean
//    NewTopic createTopic(){
//        return TopicBuilder.name("names").build();
//    }



}





