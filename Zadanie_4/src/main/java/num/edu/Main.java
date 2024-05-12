package num.edu;

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


    public static void main(String[] args) {

        Double epsilon;
        int nodesNumber;
        int algChoice;

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

        System.out.println("Wybierz kwadraturę:");
        System.out.println("1. Kwadratura Newtona-Cotesa");
        System.out.println("2. Kwadratura Gaussa-Czebyszewa");
        algChoice = scan.nextInt();
        if (algChoice == 1) {
            System.out.println("Podaj dokładność (ε):");
            epsilon = scan.nextDouble();
            NewtonCotes newtonCotes = new NewtonCotes();
            System.out.println("Podaj czy z wagą czy bez: {1 - z, 2 - bez}");
            int choice = scan.nextInt();
            if (choice == 1) {
                newtonCotes.weightCond = true;
            } else {
                newtonCotes.weightCond = false;
            }
            System.out.println("Wynik dla kwadratury Newtona-Cotesa: " + newtonCotes.limForSimpson(mathFunction,epsilon));
        } else {
            System.out.println("Podaj liczbę węzłów (2-5):");
            nodesNumber = scan.nextInt();
            GaussCzebyszew gaussCzebyszew = new GaussCzebyszew();
            System.out.println("Wynik dla kwadratury Gaussa-Czebyszewa: " + gaussCzebyszew.resultsGC(nodesNumber, mathFunction));
        }


    }
}