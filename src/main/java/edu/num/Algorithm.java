package edu.num;

public interface Algorithm {
    Double algorithm(Double a, Double b, Integer iter, int choice);
    Double algorithm(Double a, Double b, Double eps, int choice);
}
