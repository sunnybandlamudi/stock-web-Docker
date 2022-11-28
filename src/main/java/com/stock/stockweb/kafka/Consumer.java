package com.stock.stockweb.kafka;

import com.google.gson.Gson;
import com.stock.stockweb.models.Poem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @Autowired
    Gson gson;

    @KafkaListener(groupId = "name-group",topics = "names")
    void listen(String name){


        Poem p =  gson.fromJson(name, Poem.class);

        log.info(String.format("Consumer Details %s",p.getId()));

        System.out.println(name);
    }
}
