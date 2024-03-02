package edu.num;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.Math;
import java.util.Scanner;

public class Main {

    public static Funkcja choice(int wybor) {
        Funkcja funkcja;
        if (wybor == 1) {
            funkcja = new FunkcjaSin();
        } else {
            funkcja = new FunkcjaKwadratowa();
        }
        return funkcja;
    }

    public static void plotGenerator(int wybor, Double a, Double b, String outputFileName, int numberOfPoints) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));) {
            StringBuilder content = new StringBuilder();
            Funkcja function = choice(wybor);
            Double range = b - a;
            Double step = range / numberOfPoints;
            for (int i = 0; i < numberOfPoints; i++) {
                content.append(a + i * step);
                content.append(" ");
                Double value = function.oblicz(a + i * step);
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
        System.out.println("Wpisz liczbę dolnego zakresu: ");
        Double zakres_dolny = scan.nextDouble();
        System.out.println("Wpisz liczbę gornego zakresu: ");
        Double zakres_gorny = scan.nextDouble();

        System.out.println("Wybierz funkcje:");
        System.out.println("1. sin(x)");
        System.out.println("2. x^2 - 4");
        int wybor = scan.nextInt();
        System.out.println("Wybierz warunek stopu: ");
        System.out.println("1. Liczba iteracji");
        System.out.println("2. Epislon");
        Integer wyborStopu = scan.nextInt();


        Integer iteracje;
        Double epsilon;
        Algorithm algorithm = new Bisection();

        if (wyborStopu == 1) {
            System.out.println("Wpisz maksymalna liczbę iteracji: ");
            iteracje = scan.nextInt();
            System.out.println(algorithm.algorithm(zakres_dolny, zakres_gorny, iteracje, wybor));
        } else if (wyborStopu == 2) {
            System.out.println("Wpisz liczbę epislon: ");
            epsilon = scan.nextDouble();
            System.out.println(algorithm.algorithm(zakres_dolny, zakres_gorny, epsilon, wybor));
        }





        plotGenerator(wybor, zakres_dolny, zakres_gorny, "data/wyniki.txt", 100);
    }
}