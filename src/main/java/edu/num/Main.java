package edu.num;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static MathFunction mathFunctionChoice(int functionChoice) {
        MathFunction mathFunction;
        if (functionChoice == 1) {
            mathFunction = new LogarithmicFunction();
        } else if (functionChoice == 2) {
            mathFunction = new QuadraticFunction();
        } else if (functionChoice == 3) {
            mathFunction = new PolynomialFunction();
        } else if (functionChoice == 4) {
            mathFunction = new ExponentialFunction();
        } else if (functionChoice == 5) {
            mathFunction = new ExpandedSineFunction();
        } else {

            System.out.println("Podaj stopień wielomianu: ");
            int degree = scan.nextInt();
            double[] coefficient = new double[degree + 1];
            for (int i = degree; i >= 0; i--) {
                if (i != 0) {
                    System.out.println("Podaj współczynnik dla x^"+i);
                    coefficient[i] = scan.nextDouble();
                } else {
                    System.out.println("Podaj wyraz wolny:");
                    coefficient[i] = scan.nextDouble();
                }

            }

            mathFunction = new Horner(coefficient);
//            for (int i = degree; i >= 0; i--) {
//                if (i != 0) {
//                    System.out.print(coefficient[i]+"x^"+i+" + ");
//                } else {
//                    System.out.println(coefficient[i]);
//                }
//            }
        }
        return mathFunction;
    }

    public static void plotGenerator(MathFunction mathFunction, Double a, Double b, String outputFileName, int numberOfPoints, Double solution) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            StringBuilder content = new StringBuilder();
//            MathFunction mathFunction = mathFunctionChoice(functionChoice);
            content.append(solution);
            content.append(" ");
            content.append(mathFunction.calculate(solution));
            content.append("\n");
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

    public static void main(String[] args) {

        System.out.println("Wybierz algorytm:");
        System.out.println("1. Reguła bisekcji");
        System.out.println("2. Reguła Falsi");
        Integer algorithmChoice = scan.nextInt();

        System.out.println("Wybierz funkcję:");
        System.out.println("1. log10(x) * (3 - x) * x + 5   (miejsce zerowe 4,625)");
        System.out.println("2. x^2 - 4    (miejsce zerowe -2 oraz 2)");
        System.out.println("3. 4x^5 + 7x^3 + x^2 - 7    (miejsce zerowe ~0,8)");
        System.out.println("4. e^(x - 7) - 4   (miejsce zerowe ~8)");
        System.out.println("5. sin(0,1x + 2,5)   (miejsce zerowe 6,416 + 2k*Pi)");
        System.out.println("6. Dowolny wielomian");

        int funChoice = scan.nextInt();

        MathFunction mathFunction = Main.mathFunctionChoice(funChoice);

        System.out.println("Wpisz wartość dolnego zakresu: ");
        Double bottomRange = scan.nextDouble();
        System.out.println("Wpisz wartość gornego zakresu: ");
        Double topRange = scan.nextDouble();

        System.out.println("Wybierz warunek stopu: ");
        System.out.println("1. Liczba iteracji");
        System.out.println("2. Dokładność (ε)");
        Integer stopChoice = scan.nextInt();


        Integer iter;
        Double eps;
        Algorithm algorithm;

        if (algorithmChoice == 1) {
            algorithm = new Bisection();
        } else {
            algorithm = new Falsi();
        }

        Double sol;

        if (stopChoice == 1) {
            System.out.println("Wpisz maksymalną liczbę iteracji: ");
            iter = scan.nextInt();
            sol = algorithm.algorithm(bottomRange, topRange, iter, mathFunction);
            System.out.println(sol);

        } else { //if (stopChoice == 2) {
            System.out.println("Wpisz dokładność (ε): ");
            eps = scan.nextDouble();
            sol = algorithm.algorithm(bottomRange, topRange, eps, mathFunction);
            System.out.println(sol);
        }

        plotGenerator(mathFunction, bottomRange, topRange, "data/wyniki.txt", 200, sol);
    }
}