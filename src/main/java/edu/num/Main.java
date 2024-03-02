package edu.num;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static MathFunction choice(int functionChoice) {
        MathFunction mathFunction;
        if (functionChoice == 1) {
            mathFunction = new SineFunction();
        } else if (functionChoice == 2) {
            mathFunction = new QuadraticFunction();
        } else {
            mathFunction = new PolynomialFunction();
        }
        return mathFunction;
    }

    public static void plotGenerator(int functionChoice, Double a, Double b, String outputFileName, int numberOfPoints) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));) {
            StringBuilder content = new StringBuilder();
            MathFunction mathFunction = choice(functionChoice);
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
        System.out.println("Wpisz wartość dolnego zakresu: ");
        Double bottomRange = scan.nextDouble();
        System.out.println("Wpisz wartość gornego zakresu: ");
        Double topRange = scan.nextDouble();

        System.out.println("Wybierz funkcję:");
        System.out.println("1. sin(x)");
        System.out.println("2. x^2 - 4");
        System.out.println("3. 4x^5 + 7x^3 + x^2 - 7");

        int stopCondition = scan.nextInt();
        System.out.println("Wybierz warunek stopu: ");
        System.out.println("1. Liczba iteracji");
        System.out.println("2. Dokładność (ε)");
        Integer stopChoice = scan.nextInt();


        Integer iter;
        Double eps;
        Algorithm algorithm = new Bisection();

        if (stopChoice == 1) {
            System.out.println("Wpisz maksymalną liczbę iteracji: ");
            iter = scan.nextInt();
            System.out.println(algorithm.algorithm(bottomRange, topRange, iter, stopCondition));
        } else if (stopChoice == 2) {
            System.out.println("Wpisz dokładność (ε): ");
            eps = scan.nextDouble();
            System.out.println(algorithm.algorithm(bottomRange, topRange, eps, stopCondition));
        }





        plotGenerator(stopCondition, bottomRange, topRange, "data/wyniki.txt", 100);
    }
}