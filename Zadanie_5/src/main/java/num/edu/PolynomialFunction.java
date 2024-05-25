package num.edu;

public class PolynomialFunction implements MathFunction {
    @Override
    public Double calculate(Double x) {
        return ((4 * x * x + 7) * x + 1) * x * x - 7;
    }
//    public Double calculate(Double x) {
//        return 2*x*x*x-18*x*x+54*x-54;
//    }
//        public Double calculate(Double x) {
//        return x*x*x*x-x*x*x-x*x-x+1;
//    }

}
