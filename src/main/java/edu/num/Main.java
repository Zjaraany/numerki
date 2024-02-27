package edu.num;

import javax.imageio.IIOException;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

interface Funkcja {
    public double oblicz(double x);
}

class FunkcjaSin implements Funkcja {
    
    public double oblicz(double x) {
        return Math.sin(x);
    }
}

class FunkcjaKwadratowa implements Funkcja {
    public double oblicz(double x) {
        return x * x;
    }
}

public class Main {

    public static Double funkcja_sin(Double x) {
        return Math.sin(x);
    }

    public static Double funkcja(Double x) {
        return x*x-4;
    }

    public static Double algorytm(Double a, Double b, Double epsilon, Integer iteracje, int wybor) {
        int i = 1;

        Double wynik = (a + b) / 2;
        Double poprzedni = 0d;
        
        Funkcja funkcja;
        
        if (wybor == 1) {
            funkcja = new FunkcjaSin();
        }

        if (funkcja(wynik) == 0) {
            return wynik;
        } else {
            while (i <= iteracje || Math.abs(wynik - poprzedni) >= epsilon) {
                poprzedni = wynik;

                if (funkcja(a)*funkcja(wynik) < 0) {
                    b = wynik;
                } else {
                    a = wynik;
                }
                wynik = (a + b) / 2;

                i++;
            }
            return wynik;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Wpisz liczbę dolnego zakresu: ");
        Double zakres_dolny = scan.nextDouble();
        System.out.println("Wpisz liczbę gornego zakresu: ");
        Double zakres_gorny = scan.nextDouble();
        System.out.println("Wpisz maksymalna liczbę iteracji: ");
        Integer iteracje = scan.nextInt();
        System.out.println("Wpisz liczbę epislon: ");
        Double epsilon = scan.nextDouble();

        System.out.println("Wybierz funkcje:");
        System.out.println("1. sin(x)");
        System.out.println("2. x^2 - 4");

        Integer wybor;



        System.out.println(algorytm(zakres_gorny, zakres_dolny, epsilon, iteracje, wybor.intValue()));

    }
}