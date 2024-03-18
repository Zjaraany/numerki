package edu.num;

public class Bisection implements Algorithm {

    public Double algorithm(Double a, Double b, Integer iter, MathFunction mathFunction) {
        int i = 0;

        Double result = (a + b) / 2;


//        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);


        while (i < iter) {
            i++;
            result = (a + b) / 2;

            Double value = mathFunction.calculate(result);
            Double valueA = mathFunction.calculate(a);
            Double valueB = mathFunction.calculate(b);

            if (value == 0) {
                System.out.println("Otrzymano rozwiązanie przy mniejszej liczbie iteracji: "+i);
                return result;
            } else if (valueA * value < 0) {
                b = result;
            } else if (valueB * value < 0) {
                a = result;
            }


        }

        return result;

    }

    public Double algorithm(Double a, Double b, Double eps, MathFunction mathFunction) {

        Double result = (a + b) / 2;
        Double previous = a;
        int counter = 0;

//        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

            while (Math.abs(result - previous) >= eps) {
                counter++;
                previous = result;
                Double value = mathFunction.calculate(result);
                Double valueA = mathFunction.calculate(a);
                Double valueB = mathFunction.calculate(b);
                if (value == 0) {
                    System.out.println("Liczba iteracji do osiągnięcia oczekiwanej dokładności: " + counter);
                    return result;
                } else if (valueA * value < 0) {
                    b = result;
                } else if (valueB * value < 0) {
                    a = result;
                }
                result = (a + b) / 2;
            }
            System.out.println("Liczba iteracji do osiągnięcia oczekiwanej dokładności: " + counter);
            return result;
//        }
    }
}
