package num.edu;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
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

    public static void plotGeneratorNewton(NewtonInterpolation newtonInterpolation, Double[] coefficients, Double a, Double b, String outputFileName, int numberOfPoints) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            StringBuilder content = new StringBuilder();
            Double range = b - a;
            Double step = range / numberOfPoints;
            for (int i = 0; i <= numberOfPoints; i++) {
                content.append(a + i * step);
                content.append(" ");
                Double value = newtonInterpolation.calculateFromNewton(coefficients,a + i * step);
                content.append(value);
                content.append("\n");
            }
            writer.write(content.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void specialPointsGenerator(MathFunction mathFunction, Double [] x, String outputFileName, int numberOfPoints) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < numberOfPoints; i++) {
                content.append(x[i]);
                content.append(" ");
                content.append(mathFunction.calculate(x[i]));
                content.append("\n");
            }
            writer.write(content.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Double [] generateRandomNodes(Double rangeA, Double rangeB, Integer nodes) {
        Double partLength = ((rangeB-rangeA)/(nodes+1));
        Double nodesX [] = new Double[nodes];
        Random random = new Random();
        nodesX[0] = rangeA + partLength + random.nextDouble() * partLength - partLength / 2;;
        for (int i = 1; i < nodes; i++) {
            nodesX[i] = nodesX[i-1] + partLength + random.nextDouble() * partLength - partLength / 2;;
        }
        return nodesX;
    }

    public static void main(String[] args) {

        Double valA;
        Double valB;
        Integer nodesNumber;
        Double[] nodeX;

        System.out.println("Wybierz, w jaki sposób chcesz przekazać węzły.");
        System.out.println("1. Z pliku");
        System.out.println("2. Generuj losowe węzły");

        Integer nodesChoice = scan.nextInt();

        if (nodesChoice == 1) {
            System.out.println("Podaj dolną wartość przedziału:");
            valA = scan.nextDouble();
            System.out.println("Podaj górną wartość przedziału:");
            valB = scan.nextDouble();
            System.out.println("Podaj liczbę węzłów:");
            nodesNumber = scan.nextInt();
            nodeX = new Double[nodesNumber];
            try {
                File file = new File("data/wezlyX");
                Scanner fileScanner = new Scanner(file);
                if (fileScanner.hasNext()) {
                    for (int i = 0; i < nodesNumber; i++) {
                        nodeX[i] = Double.parseDouble(fileScanner.nextLine());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Podaj dolną wartość przedziału:");
            valA = scan.nextDouble();
            System.out.println("Podaj górną wartość przedziału:");
            valB = scan.nextDouble();
            System.out.println("Podaj liczbę węzłów:");
            nodesNumber = scan.nextInt();
            System.out.println("Wygenerowano następujące węzły:");
            nodeX = generateRandomNodes(valA, valB, nodesNumber);
            System.out.println(Arrays.toString(nodeX));
        }

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

        System.out.println("Wyznaczono następujący wielomian interpolacyjny Newtona:");
        NewtonInterpolation newtonInterpolation = new NewtonInterpolation(mathFunction, valA, valB, nodeX);

        Double[] quotients = newtonInterpolation.getDifferenceQuotients();
        System.out.println(newtonInterpolation.writeNewtonPolynomial(quotients));

        Main.specialPointsGenerator(mathFunction, nodeX, "data/specialPoints", nodesNumber);
        Main.plotGeneratorOriginal(mathFunction, valA, valB, "data/original", 200);
        Main.plotGeneratorNewton(newtonInterpolation, quotients, valA, valB, "data/Newton", 200);

    }
}