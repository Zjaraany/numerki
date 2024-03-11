package edu.num;

public class Falsi implements Algorithm {
    public Double algorithm(Double a, Double b, Integer iter, int functionChoice) {
        int i = 1;

        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

        Double chordResult = a - (mathFunction.calculate(a) * (b - a)/(mathFunction.calculate(b)- mathFunction.calculate(a)));

        Double value = mathFunction.calculate(chordResult);

        if (value == 0) {
            return chordResult;
        } else {
            while (i <= iter) {
                value = mathFunction.calculate(chordResult);
                Double valueA = mathFunction.calculate(a);
                if (valueA * value < 0) {
                    b = chordResult;
                } else {
                    a = chordResult;
                }
                chordResult = a - (mathFunction.calculate(a) * (b - a)/(mathFunction.calculate(b)- mathFunction.calculate(a)));

                i++;
            }
            return chordResult;
        }
    }

    public Double algorithm(Double a, Double b, Double eps, int functionChoice) {
        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

        Double chordResult = a - (mathFunction.calculate(a) * (b - a)/(mathFunction.calculate(b)- mathFunction.calculate(a)));

        Double previous = Double.POSITIVE_INFINITY;
        int counter = 0;

        Double value = mathFunction.calculate(chordResult);
        if (value == 0) {
            return chordResult;
        } else {
            while (Math.abs(chordResult - previous) >= eps) {
                counter++;
                previous = chordResult;
                value = mathFunction.calculate(chordResult);
                Double valueA = mathFunction.calculate(a);
                if (valueA * value < 0) {
                    b = chordResult;
                } else {
                    a = chordResult;
                }
                chordResult = a - (mathFunction.calculate(a) * (b - a)/(mathFunction.calculate(b)- mathFunction.calculate(a)));
            }
            System.out.println("Liczba iteracji do osiągnięcia oczekiwanej dokładności: "+counter);
            return chordResult;
        }
    }
}
