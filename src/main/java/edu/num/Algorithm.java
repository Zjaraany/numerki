package edu.num;

public interface Algorithm {
    Double algorithm(Double a, Double b, Integer iter, int functionChoice);
    Double algorithm(Double a, Double b, Double eps, int functionChoice);
}
