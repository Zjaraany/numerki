package edu.num;

public interface Algorithm {
    Double algorithm(Double a, Double b, Integer iter, MathFunction mathFunction);
    Double algorithm(Double a, Double b, Double eps, MathFunction mathFunction);
}
