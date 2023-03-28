package com.signalsender.controller;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.signalsender.service.SignalGenerator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;





@RestController
public class SenderController2 {

    private final SignalGenerator signalGenerator;
    private final WebClient webClient;
    
//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @Autowired
    public SenderController2(SignalGenerator signalGenerator, WebClient.Builder webClientBuilder) {
        System.out.println("SignalGenerator: " + signalGenerator);
        System.out.println("WebClient.Builder: " + webClientBuilder);
        this.signalGenerator = signalGenerator;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    @GetMapping("/send-signals")
    public void sendSignals() {
        // Create a Flux to emit signals at a fixed interval
//        Flux<int[]> signalFlux = Flux.interval(Duration.ofSeconds(5))
//                .map(i -> signalGenerator.generateSignal());
//    	
    	
    	System.out.println("Sending Signals");
    	//1
//    	Flux<Object> signalFlux = Flux.interval(Duration.ofSeconds(5))
//    		    .map(i -> Flux.generate(sink -> {
//    		    	System.out.println("1");
//    		        int[] signal = signalGenerator.generateSignal(); 
//    		        System.out.println("2");
//
//        // Use WebClient to send signals to ReceiverMicroService
//    		    webClient.post()
//                .uri("/processSignal")
//                .body(signal, int[].class)
//                .retrieve()
//                .bodyToMono(String.class)
//                .subscribe(response -> System.out.println("Received response: " + response),
//                        error -> System.out.println("Error occurred: " + error),
//                        () -> System.out.println("Response Flux completed"));
//    		    
//    		    sink.next(signal); 
//    		    sink.complete();
//    		    }));
    	
    	
    	//2
//    	Flux<int[]> signalFlux = Flux.interval(Duration.ofSeconds(5))
//    		    .map(i -> signalGenerator.generateSignal())
//    		    .map(signal -> {
//    		        System.out.println("1");
//    		        webClient.post()
//    		            .uri("/processSignal")
//    		            .body(signal, int[].class)
//    		            .retrieve()
//    		            .bodyToFlux(String.class)
//    		            .subscribe(response -> System.out.println("Received response: " + response),
//    		                error -> System.out.println("Error occurred: " + error),
//    		                () -> System.out.println("Response Flux completed"));
//    		        return signal;
//    		    });
    	
    	//3
    	Flux<int[]> signalFlux = Flux.interval(Duration.ofSeconds(5))
    		    .map(i -> {
    		        //System.out.println("1");
    		        return signalGenerator.generateSignal();
    		    })
    		    .doOnNext(signal -> {
    		    	//System.out.println(signal.getClass().getName());
    		        webClient.post()
    		            .uri("/processSignal")
    		            .body(Mono.just(signal), int[].class)
    		            .retrieve()
    		            .bodyToFlux(String.class)
    		            .subscribe(response -> System.out.println("Received response: " + response),
    		                       error -> System.out.println("Error occurred: " + error));
    		    });
    	signalFlux.subscribe();

    		




    		    
    	
    	
    	
    	
    	
    	
    	
    	
    	//.subscribe(response -> System.out.println("Received response: " + response));
        
//        int[] signals = Flux.generate(signalGenerator.generateSignal()) // generate the signal
//        .delayElements(Duration.ofSeconds(5)) // add a delay of 5 seconds between each signal
//        .flatMap( signal -> webClient.post() // use flatMap to send each signal to the ReceiverMicroService
//            .uri("/receive-signal")
//            .bodyValue(signal)
//            .retrieve()
//            .bodyToFlux(String.class)
//            .doOnNext(response -> System.out.println("Received response: " + response))
//        );
//    	
    	
    	//Flux.create(signalFluxSink -> {
            //while (true) {
//                int[] signal = signalGenerator.generateSignal();
//                //signalFluxSink.next(signal);
//                try {
//                // Use WebClient to send the signal to the other microservice
//                Mono<String> response = webClientBuilder.build()
//                		.post()
//                        .uri("http://localhost:8081/processSignal")
//                        .body(Mono.just(signal), int[].class)
//                        .retrieve()
//                        .bodyToMono(String.class);
//                        
//                
//                response.subscribe(System.out::println);
//                }catch(Exception e) {
//                	System.out.println(e);
//                }
                
//                try {
//                    Thread.sleep(10000); // 1000 milliseconds = 1 second
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            //}
        //}).subscribe();
        
        
    }
}
