package edu.num;

public class PolynomialFunction implements MathFunction {
    @Override
    public Double calculate(Double x) {
        return ((4 * x * x + 7) * x + 1) * x * x - 7;
    }
}
