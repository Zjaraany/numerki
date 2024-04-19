package num.edu;

public class Main {
    public static void main(String[] args) {
        MathFunction mathFunction = new PolynomialFunction();
//        Double[] x = {-0.926, -0.395, 0.895};
        Double[] x = {-1d, 0.5d, 1d};

        NewtonInterpolation newtonInterpolation = new NewtonInterpolation(mathFunction, x);
        newtonInterpolation.writeNewtonPolynomial(newtonInterpolation.getResults());
        System.out.println(newtonInterpolation.calculateFromNewton(newtonInterpolation.getResults(), 0.5d));
        System.out.println(mathFunction.calculate(0.5d));

    }
}