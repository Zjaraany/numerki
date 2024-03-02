package edu.num;

public class Bisection implements Algorithm {

    public Double algorithm(Double a, Double b, Integer iter, int functionChoice) {
        int i = 1;

        Double result = (a + b) / 2;

        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);
        
        Double value = mathFunction.calculate(result);
        if (value == 0) {
            return result;
        } else {
            while (i <= iter) {
                value = mathFunction.calculate(result);
                Double valueA = mathFunction.calculate(a);
                if (valueA * value < 0) {
                    b = result;
                } else {
                    a = result;
                }
                result = (a + b) / 2;

                i++;
            }
            return result;
        }
    }

    public Double algorithm(Double a, Double b, Double eps, int functionChoice) {

        Double result = (a + b) / 2;
        Double previous = Double.POSITIVE_INFINITY;

        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

        Double value = mathFunction.calculate(result);
        if (value == 0) {
            return result;
        } else {
            while (Math.abs(result - previous) >= eps) {
                previous = result;
                value = mathFunction.calculate(result);
                Double valueA = mathFunction.calculate(a);
                if (valueA * value < 0) {
                    b = result;
                } else {
                    a = result;
                }
                result = (a + b) / 2;
            }
            return result;
        }
    }
}
