package com.jianhaoweb.config;

import java.util.Random;

public class NeuralNetwork {
    private int inputSize;   // 输入层大小
    private int hiddenSize;  // 隐层大小
    private int outputSize;  // 输出层大小
    private double[][] weightsIH;  // 输入层到隐层的权重矩阵
    private double[][] weightsHO;  // 隐层到输出层的权重矩阵
    private double[] hidden;  // 隐层神经元的输出
    private double[] output;  // 输出层神经元的输出

    // 构造函数，初始化神经网络的参数
    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
        this.weightsIH = new double[inputSize][hiddenSize];
        this.weightsHO = new double[hiddenSize][outputSize];
        this.hidden = new double[hiddenSize];
        this.output = new double[outputSize];
        initializeWeights();
    }

    // 初始化权重矩阵
    private void initializeWeights() {
        Random random = new Random();
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                weightsIH[i][j] = random.nextDouble() * 2 - 1;
            }
        }
        for (int i = 0; i < hiddenSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                weightsHO[i][j] = random.nextDouble() * 2 - 1;
            }
        }
    }

    // 前向传播，计算输出
    public double[] feedForward(double[] inputs) {
        // 计算隐层神经元的输出
        for (int i = 0; i < hiddenSize; i++) {
            double sum = 0.0;
            for (int j = 0; j < inputSize; j++) {
                sum += inputs[j] * weightsIH[j][i];
            }
            hidden[i] = sigmoid(sum);
        }
        // 计算输出层神经元的输出
        for (int i = 0; i < outputSize; i++) {
            double sum = 0.0;
            for (int j = 0; j < hiddenSize; j++) {
                sum += hidden[j] * weightsHO[j][i];
            }
            output[i] = sigmoid(sum);
        }
        return output;
    }

    // Sigmoid 激活函数
    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }




}
