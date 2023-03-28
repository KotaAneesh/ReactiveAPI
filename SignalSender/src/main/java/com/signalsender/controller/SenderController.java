package com.signalsender.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class SenderController {

    private static final Random random = new Random();

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("sendSignal")
    public void sendSignal() {
    	//while(true) {
        int[] signal = random.ints((int)Math.floor(Math.random() * 11), 0, 100).toArray(); // generate a random array
        Mono<String> response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/processSignal")
                .body(Mono.just(signal), int[].class)
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(System.out::println);
        
//        try {
//            Thread.sleep(10000); // 1000 milliseconds = 1 second
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        
    	}
    //}

}