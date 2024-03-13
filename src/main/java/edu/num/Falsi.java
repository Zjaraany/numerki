package edu.num;

public class Falsi implements Algorithm {
    public Double algorithm(Double a, Double b, Integer iter, int functionChoice) {
        int i = 0;

        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

        Double chordResult = a - (mathFunction.calculate(a) * (b - a)/(mathFunction.calculate(b)- mathFunction.calculate(a)));

        Double value;


        while (i < iter) {
            i++;
            value = mathFunction.calculate(chordResult);
            Double valueA = mathFunction.calculate(a);
            Double valueB = mathFunction.calculate(b);
            if (value == 0) {
                System.out.println("Otrzymano rozwiązanie przy mniejszej liczbie iteracji: "+i);
                return chordResult;
            } else if (valueA * value < 0) {
                b = chordResult;
            } else if (valueB * value < 0) {
                a = chordResult;
            }
            chordResult = a - (mathFunction.calculate(a) * (b - a)/(mathFunction.calculate(b)- mathFunction.calculate(a)));
        }

        return chordResult;
    }

    public Double algorithm(Double a, Double b, Double eps, int functionChoice) {
        MathFunction mathFunction = Main.mathFunctionChoice(functionChoice);

        Double chordResult = a - ((mathFunction.calculate(a) * (b - a))/(mathFunction.calculate(b)- mathFunction.calculate(a)));
        Double previous = b;

        int counter = 0;

        Double value;// = mathFunction.calculate(chordResult);

        while (Math.abs(chordResult - previous) >= eps) {
            counter++;
            previous = chordResult;
            value = mathFunction.calculate(chordResult);
            Double valueA = mathFunction.calculate(a);
            Double valueB = mathFunction.calculate(b);
            if (value == 0) {
                return chordResult;
            } else if (valueA * value < 0) {
                b = chordResult;
            } else if (valueB * value < 0) {
                a = chordResult;
            }
            chordResult = a - (mathFunction.calculate(a) * (b - a)/(mathFunction.calculate(b)- mathFunction.calculate(a)));
        }

        System.out.println("Liczba iteracji do osiągnięcia oczekiwanej dokładności: "+counter);
        return chordResult;

    }
}
