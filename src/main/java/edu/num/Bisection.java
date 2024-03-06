package edu.num;

public class Bisection implements Algorithm {

    public Double algorithm(Double a, Double b, Integer iter, int functionChoice) {
        int i = 1;

        Double result = (a + b) / 2;

        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

        while (i <= iter) {
            result = (a + b) / 2;

            Double value = mathFunction.calculate(result);
            Double valueA = mathFunction.calculate(a);
            Double valueB = mathFunction.calculate(b);

            if (value == 0) {
                return result;
            } else if (valueA * value < 0) {
                b = result;
            } else if (valueB * value < 0) {
                a = result;
            }

            i++;
        }

        return result;





//        Double result = (a + b) / 2;
//
//        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);
//
//        Double value = mathFunction.calculate(result);
//        if (value == 0) {
//            return result;
//        } else {
//            while (i <= iter) {
//                value = mathFunction.calculate(result);
//                Double valueA = mathFunction.calculate(a);
//                Double valueB = mathFunction.calculate(b);
//                if (valueA * value < 0) {
//                    b = result;
//                } else if (value * valueB < 0) {
//                    a = result;
//                } else if (value == 0) {
//
//                }
//                result = (a + b) / 2;
//
//                i++;
//            }
//            return result;
//        }
    }

    public Double algorithm(Double a, Double b, Double eps, int functionChoice) {

        Double result = (a + b) / 2;
        Double previous = a;

        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

            while (Math.abs(result - previous) >= eps) {
                previous = result;
                Double value = mathFunction.calculate(result);
                Double valueA = mathFunction.calculate(a);
                Double valueB = mathFunction.calculate(b);
                if (value == 0) {
                    return result;
                } else if (valueA * value < 0) {
                    b = result;
                } else if (valueB * value < 0) {
                    a = result;
                }
                result = (a + b) / 2;
            }
            return result;
//        }
    }
}
