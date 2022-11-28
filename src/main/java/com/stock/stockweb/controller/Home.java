package com.stock.stockweb.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stock.stockweb.kafka.Producer;
import com.stock.stockweb.models.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@RestController
public class Home {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Producer producer;

    String url = "https://jsonplaceholder.typicode.com/posts";

    @GetMapping("/")
    ResponseEntity<?> home() throws IOException {

        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", String.class);

        Gson g = new Gson();

        Type poemsType = new TypeToken<List<Poem>>(){}.getType();
        List<Poem> poem = g.fromJson(forEntity.getBody(), poemsType);

        poem.stream().forEach((item) -> producer.send("names",g.toJson(item)));

        return forEntity;
    }

}
