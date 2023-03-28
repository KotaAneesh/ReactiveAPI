package com.signalreciever.service;

public class MLModel {
    private final double[][] weights;
    private final double[] biases;

    public MLModel(double[][] weights, double[] biases) {
        this.weights = weights;
        this.biases = biases;
    }

    public int predict(int[] input) {
        double[] output = new double[2]; // we assume there are only 2 possible labels (0 or 1)
        for (int i = 0; i < weights.length; i++) {
            double sum = biases[i];
            for (int j = 0; j < weights[i].length; j++) {
                sum += input[j] * weights[i][j];
            }
            output[i] = sigmoid(sum);
        }
        return output[0] > output[1] ? 0 : 1; // return the label with the highest probability
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
