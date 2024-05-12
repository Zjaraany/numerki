package num.edu;

public class PolynomialHorner implements MathFunction {

    private double[] coefficient;

    PolynomialHorner(double[] coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public Double calculate(Double x) {
        int len = coefficient.length;

        if (len == 1) {
            return coefficient[0];
        } else {
            double result = coefficient[len - 1];

            for (int i = len - 2; i >= 0; i--) {
                result *= x;
                result += coefficient[i];
            }

            return result;
        }
    }
}
