package num.edu;

public class GaussCzebyszew {

    Double resultsGC (int nodes, MathFunction mathFunction, int N, Double a, Double b) {
        Double sum = 0.0;
        Double A = Math.PI/nodes;
        CzebyszewPolynomial czebyszewPolynomial = new CzebyszewPolynomial(N);
        for (int i = 0; i < nodes; i++) {
            Double x = Math.cos((2*i+1)*Math.PI/(2*nodes));
            Double t = 0.5 * (a + b + (b - a) * x);
            sum += A * mathFunction.calculate(t) * czebyszewPolynomial.calculate(x);
        }
        return sum;
    }

}
