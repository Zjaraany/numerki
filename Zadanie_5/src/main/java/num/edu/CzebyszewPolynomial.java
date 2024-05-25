package num.edu;

public class CzebyszewPolynomial implements MathFunction {

    private int n;

    public CzebyszewPolynomial(int n) {
        this.n = n;
    }

    @Override
    public Double calculate(Double x) {
        if (n == 0) {
            return 1.0;
        } else if (n == 1) {
            return x;
        } else {
            Double n1 = x;
            Double n2 = 1.0;
            Double nCurrent = 0.0;
            for (int i = 2; i <= n; i++) {
                nCurrent = (2.0 * x * n1) - n2;
                n2 = n1;
                n1 = nCurrent;
            }
            return nCurrent;
        }
    }
}
