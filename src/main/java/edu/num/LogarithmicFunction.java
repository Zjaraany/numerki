package edu.num;

class LogarithmicFunction implements MathFunction {

    public Double calculate(Double x) {
        return Math.log10(x) * (3 - x) * x + 5;
    }
}
