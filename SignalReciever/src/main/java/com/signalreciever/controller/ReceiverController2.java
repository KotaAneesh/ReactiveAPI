package com.signalreciever.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.signalreciever.service.MLModel;

import reactor.core.publisher.Flux;

@RestController
public class ReceiverController2 {
	
	double[][] weights = {{0.1, 0.2, 0.3}, {-0.2, -0.1, -0.3}};
	double[] biases = {0.1, -0.1};
	MLModel model = new MLModel(weights, biases);

    @PostMapping("/receive-signal")
    public Flux<String> receiveSignal(@RequestBody Flux<int[]> signalFlux) {
        return signalFlux.map(signal -> {
        	int label = model.predict(signal);
            String result = "Signal processed: " + Arrays.toString(signal)+" and label is: "+label;
//            System.out.println("1");
            return result;
        });
    }
} 

