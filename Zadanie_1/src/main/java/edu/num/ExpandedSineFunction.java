package edu.num;

public class ExpandedSineFunction implements MathFunction{
    @Override
    public Double calculate(Double x) {return Math.sin(0.1 * x + 2.5);}
}
