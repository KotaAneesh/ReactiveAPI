package com.signalsender.service;

import java.util.Random;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import reactor.core.publisher.SynchronousSink;

@Service
public class SignalGenerator {
	
	Random random = new Random();
	
	public int[] generateSignal() {
		int[] signal = random.ints((int)Math.floor(Math.random() * 11), 0, 100).toArray();
		for(int i=0;i<signal.length;i++) {
			System.out.print(signal[i]+" ");
		}
		System.out.println();
		return signal;
	}
	
//	signalGenerator = (sink) -> {
//	    int[] signals = new int[] {1, 2, 3, 4, 5};
//	    for (int signal : signals) {
//	        sink.next(signal);
//	    }
//	    sink.complete();
//	};
	
	public static Consumer<SynchronousSink<int[]>> generateSignal2() {
	    return sink -> {
	        Random rand = new Random();
	        int numSignals = 5;
	        int signalSize = 3;
	        for (int i = 0; i < numSignals; i++) {
	            int[] signal = new int[signalSize];
	            for (int j = 0; j < signalSize; j++) {
	                signal[j] = rand.nextInt(10); // generate a random integer between 0 and 9
	            }
	            sink.next(signal); // emit the signal to the downstream operator
	        }
	        sink.complete(); // signal the completion of signal emission
	    };
	}


}
