package edu.num;

import javax.imageio.IIOException;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

interface Funkcja {
    public Double oblicz(Double x);
}

class FunkcjaSin implements Funkcja {
    
    public Double oblicz(Double x) {
        return Math.sin(x);
    }
}

class FunkcjaKwadratowa implements Funkcja {
    public Double oblicz(Double x) {
        return x * x - 4;
    }
}

public class Main {

//    public static Double funkcja_sin(Double x) {
//        return Math.sin(x);
//    }
//
//    public static Double funkcja(Double x) {
//        return x*x-4;
//    }

    public static Double algorytm(Double a, Double b, Double epsilon, Integer iteracje, int wybor) {
        int i = 1;

        Double wynik = (a + b) / 2;
        Double poprzedni = 0d;
        
        Funkcja funkcja;
        
        if (wybor == 1) {
            funkcja = new FunkcjaSin();
        } else {
            funkcja = new FunkcjaKwadratowa();
        }
        Double wartosc = funkcja.oblicz(wynik);
        if (wartosc == 0) {
            return wynik;
        } else {
            while (i <= iteracje || Math.abs(wynik - poprzedni) >= epsilon) {
                poprzedni = wynik;
                wartosc = funkcja.oblicz(wynik);
                Double wartoscA = funkcja.oblicz(a);
                if (wartoscA * wartosc < 0) {
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

    public static void plotGenerator(int wybor, Double a, Double b) {

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

        Integer wybor = scan.nextInt();

        System.out.println(algorytm(zakres_gorny, zakres_dolny, epsilon, iteracje, wybor.intValue()));


    }
}