package edu.num;

public class polynomialFunction implements Funkcja {
    @Override
    public Double oblicz(Double x) {
        return ((4 * x * x + 7) * x + 1) * x * x - 7;
    }
}
