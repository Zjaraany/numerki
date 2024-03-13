package edu.num;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    private int[] fun;


    public static MathFunction mathFunctionChoice(int functionChoice) {
        MathFunction mathFunction;
        if (functionChoice == 1) {
            mathFunction = new SineFunction();
        } else if (functionChoice == 2) {
            mathFunction = new QuadraticFunction();
        } else if (functionChoice == 3) {
            mathFunction = new PolynomialFunction();
        } else {
            mathFunction = new ExponentialFunction();
        }
        return mathFunction;
    }

    public static void plotGenerator(int functionChoice, Double a, Double b, String outputFileName, int numberOfPoints, Double solution) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            StringBuilder content = new StringBuilder();
            MathFunction mathFunction = mathFunctionChoice(functionChoice);
            content.append(solution);
            content.append(" ");
            content.append(mathFunction.calculate(solution));
            content.append("\n");
            Double range = b - a;
            Double step = range / numberOfPoints;
            for (int i = 0; i < numberOfPoints; i++) {
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

        Scanner scan = new Scanner(System.in);

        System.out.println("Wybierz algorytm:");
        System.out.println("1. Reguła bisekcji");
        System.out.println("2. Reguła Falsi");
        Integer algorithmChoice = scan.nextInt();

        System.out.println("Wybierz funkcję:");
        System.out.println("1. sin(x)");
        System.out.println("2. x^2 - 4");
        System.out.println("3. 4x^5 + 7x^3 + x^2 - 7");
        System.out.println("4. e^(x^3 - 7) - 4");

        int funChoice = scan.nextInt();

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
        Algorithm algorithm;// = new Bisection();

        if (algorithmChoice == 1) {
            algorithm = new Bisection();
        } else {
            algorithm = new Falsi();
        }

        Double sol;

        if (stopChoice == 1) {
            System.out.println("Wpisz maksymalną liczbę iteracji: ");
            iter = scan.nextInt();
            sol = algorithm.algorithm(bottomRange, topRange, iter, funChoice);
            System.out.println(sol);

        } else { //if (stopChoice == 2) {
            System.out.println("Wpisz dokładność (ε): ");
            eps = scan.nextDouble();
            sol = algorithm.algorithm(bottomRange, topRange, eps, funChoice);
            System.out.println(sol);
        }

        plotGenerator(funChoice, bottomRange, topRange, "data/wyniki.txt", 100, sol);
    }
}