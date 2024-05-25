package num.edu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static MathFunction mathFunctionChoice(int functionChoice) {
        MathFunction mathFunction;
        if (functionChoice == 1) {
            mathFunction = new LinearFunction();
        } else if (functionChoice == 2) {
            mathFunction = new SineFunction();
        } else if (functionChoice == 3) {
            mathFunction = new PolynomialFunction();
        } else if (functionChoice == 4) {
            mathFunction = new AbsoluteFunction();
        } else if (functionChoice == 5) {
            mathFunction = new ExpandedSineFunction();
        } else if (functionChoice == 6) {
            mathFunction = new CompositeFunction();
        } else {
            System.out.println("Podaj stopień wielomianu: ");
            int degree = scan.nextInt();
            double[] coefficients = new double[degree + 1];
            for (int i = degree; i >= 0; i--) {
                if (i != 0) {
                    System.out.println("Podaj współczynnik dla x^"+i);
                    coefficients[i] = scan.nextDouble();
                } else {
                    System.out.println("Podaj wyraz wolny:");
                    coefficients[i] = scan.nextDouble();
                }

            }
            mathFunction = new PolynomialHorner(coefficients);
        }
        return mathFunction;
    }

    public static void plotGeneratorOriginal(MathFunction mathFunction, Double a, Double b, String outputFileName, int numberOfPoints) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            StringBuilder content = new StringBuilder();
            Double range = b - a;
            Double step = range / numberOfPoints;
            for (int i = 0; i <= numberOfPoints; i++) {
                content.append(a + i * step);
                content.append(" ");
                Double value = mathFunction.calculate(a + i * step);
                content.append(value);
                content.append("\n");
            }
            writer.write(content.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void plotGeneratorApproximation(Approximation approximation, Double[] coefficients, Double a, Double b, String outputFileName, int numberOfPoints) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            StringBuilder content = new StringBuilder();
            Double range = b - a;
            Double step = range / numberOfPoints;
            for (int i = 0; i <= numberOfPoints; i++) {
                content.append(a + i * step);
                content.append(" ");
                Double value = approximation.approximate(coefficients,a + i * step, a, b);
                content.append(value);
                content.append("\n");
            }
            writer.write(content.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        Double valA;
        Double valB;
        int degree;
        int nodesNumber;
        int option;
        Double expectedError;

        System.out.println("Wybierz funkcję:");
        System.out.println("1. 2,8x + 39,29");
        System.out.println("2. sin(x)");
        System.out.println("3. 4x^5 + 7x^3 + x^2 - 7");
        System.out.println("4. |x|");
        System.out.println("5. sin(0,1x + 2,5)");
        System.out.println("6. cos|3x| + 8");
        System.out.println("7. Dowolny wielomian");
        int funChoice = scan.nextInt();

        MathFunction mathFunction = Main.mathFunctionChoice(funChoice);

        System.out.println("Podaj dolną wartość przedziału aproksymacji:");
        valA = scan.nextDouble();
        System.out.println("Podaj górną wartość przedziału aproksymacji:");
        valB = scan.nextDouble();

        System.out.println("Kwadratura Gaussa-Czebyszewa");
        System.out.println("Podaj liczbę węzłów:");
        nodesNumber = scan.nextInt();

        System.out.println("Wybierz tryb pracy:");
        System.out.println("1. Na 4 (wybór stopnia wielomianu)");
        System.out.println("2. Na 5 (wybór dokładności)");
        option = scan.nextInt();

        Approximation approximation = new Approximation();
        Double[] coeffs;

        if (option == 1) {
            System.out.println("Podaj stopień wielomianu aproksymacyjnego:");
            degree = scan.nextInt();
            coeffs = approximation.calculateCoefficients(mathFunction, degree, nodesNumber, valA, valB);
        } else {
            System.out.println("Podaj oczekiwany błąd aproksymacji:");
            expectedError = scan.nextDouble();
            coeffs = null;
            Double error = Double.MAX_VALUE;
            degree = 0;
            int maxDegree = 20; // maksymalny stopień wielomianu dla bezpieczeństwa

            while (error > expectedError && degree <= maxDegree) {
                coeffs = approximation.calculateCoefficients(mathFunction, degree, nodesNumber, valA, valB);
                error = approximation.calculateError(mathFunction, coeffs, valA, valB, 200);
                degree++;
            }

            if (error <= expectedError) {
                System.out.println("Osiągnięto oczekiwany błąd przy stopniu wielomianu: " + (degree - 1));
            } else {
                System.out.println("Nie udało się osiągnąć oczekiwanego błędu. Najlepszy stopień: " + (degree - 1) + ", błąd: " + error);
            }
        }

//        System.out.println("Kwadratura Gaussa-Czebyszewa");
//        System.out.println("Podaj liczbę węzłów:");
//        nodesNumber = scan.nextInt();
//
//        Approximation approximation = new Approximation();
//        Double[] coeffs = approximation.calculateCoefficients(mathFunction, degree, nodesNumber, valA, valB);

            Main.plotGeneratorOriginal(mathFunction, valA, valB, "data/original", 800);
            Main.plotGeneratorApproximation(approximation, coeffs, valA, valB, "data/approx", 800);


    }

}