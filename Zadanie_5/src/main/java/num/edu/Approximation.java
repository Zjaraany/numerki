package num.edu;

public class Approximation {
    Double[] calculateCoefficients (MathFunction mathFunction, int n, int nodes, Double a, Double b) {
        Double[] coeffs = new Double[n + 1];
        GaussCzebyszew gaussCzebyszew = new GaussCzebyszew();

        for (int i = 0; i <= n; i++) {
            Double integral = gaussCzebyszew.resultsGC(nodes, mathFunction, i, a, b);
            if (i == 0) {
                coeffs[i] = integral / Math.PI;
            } else {
                coeffs[i] = (2 / Math.PI) * integral;
            }
        }
        return coeffs;
    }

    Double transform(Double x, Double a, Double b) {
        return (2 * x - (b + a)) / (b - a);
    }

    Double inverseTransform(Double t, Double a, Double b) {
        return (b - a) * t / 2 + (b + a) / 2;
    }

    Double approximate(Double[] coeffs, Double x, Double a, Double b) {
        Double t = transform(x, a, b);
//        Double t = inverseTransform(x, a , b);
        Double result = 0.0;
        int degree = coeffs.length;

        for (int k = 0; k < degree; k++) {
            CzebyszewPolynomial czebyszewPolynomial = new CzebyszewPolynomial(k);
            result += coeffs[k] * czebyszewPolynomial.calculate(t);
        }
        return result;
    }

    public Double calculateError(MathFunction mathFunction, Double[] coeffs, Double a, Double b, int numberOfPoints) {
        Double sum = 0.0;
        Double range = b - a;
        Double step = range / numberOfPoints;

        for (int i = 0; i <= numberOfPoints; i++) {
            Double x = a + i * step;
            Double originalValue = mathFunction.calculate(x);
            Double approxValue = approximate(coeffs, x, a, b);
            sum += Math.pow(originalValue - approxValue, 2);
        }

        return Math.sqrt(sum / numberOfPoints);
    }

}