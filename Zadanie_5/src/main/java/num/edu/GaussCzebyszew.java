package num.edu;

public class GaussCzebyszew {

    Double resultsGC (int nodes, MathFunction mathFunction, int N, Double a, Double b) {
        Double sum = 0.0;
        Double A = Math.PI/nodes;
        CzebyszewPolynomial czebyszewPolynomial = new CzebyszewPolynomial(N);
        for (int i = 0; i < nodes; i++) {
//            Double x = Math.cos((2*i+1)*Math.PI/(2*nodes+2)); //o dziwo to dokładniej liczy
            Double x = Math.cos((2*i+1)*Math.PI/(2*nodes));
//            Approximation approximation = new Approximation();
//            Double t = approximation.inverseTransform(x, a, b);
//            sum = sum + A * mathFunction.calculate(x) * czebyszewPolynomial.calculate(x); //dobre

            Double t = 0.5 * (a + b + (b - a) * x); // Transformacja do przedziału [a, b]
            sum += A * mathFunction.calculate(t) * czebyszewPolynomial.calculate(x);
        }
        return sum;
    }

}
