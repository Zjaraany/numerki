package edu.num;

public class ExponentialFunction implements MathFunction {
    @Override
    public Double calculate(Double x) {
        return (Math.exp(x - 7) - 4);
    }
}
