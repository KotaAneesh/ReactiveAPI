package com.signalreciever.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.signalreciever.service.MLModel;

import reactor.core.publisher.Mono;

@RestController
public class ReceiverController {
	
	double[][] weights = {{0.1, 0.2, 0.3}, {-0.2, -0.1, -0.3}};
	double[] biases = {0.1, -0.1};
	MLModel model = new MLModel(weights, biases);


    @PostMapping("/processSignal")
    public Mono<String> processSignal(@RequestBody int[] signal) {
        int label = model.predict(signal);
        String result = "Signal processed: " + Arrays.toString(signal)+" and label is: "+label;
        return Mono.just(result);
    }

}