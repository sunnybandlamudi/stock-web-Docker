package com.stock.stockweb;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication

public class StockWebApplication {


    public static void call() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://www.nseindia.com/")
                .method("GET", null)
                .addHeader("user-agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        Set<String> names = response.headers().names();

        Map<String, List<String>> stringListMap = response.headers().toMultimap();
//        stringListMap

        Map<String, String> cookies = new HashMap<>();
        for( String cookie :stringListMap.get("set-cookie")) {
            Map<String, String> result = Arrays.asList(cookie.split(";\\s*")).stream()
                    .map(s -> s.split("="))
                    .filter((s) -> s.length > 1)
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
            cookies.putAll(result);
        }
        System.out.println(cookies);

    }

    public static void main(String[] args) {

        SpringApplication.run(StockWebApplication.class, args);
        try {
            call();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
