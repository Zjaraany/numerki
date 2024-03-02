package edu.num;

public class Bisection implements Algorithm {

    public Double algorithm(Double a, Double b, Integer iteracje, int wybor) {
        int i = 1;

        Double wynik = (a + b) / 2;

        Funkcja funkcja = Main.choice(wybor);


        Double wartosc = funkcja.oblicz(wynik);
        if (wartosc == 0) {
            return wynik;
        } else {
            while (i <= iteracje) {
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

    public Double algorithm(Double a, Double b, Double epsilon, int wybor) {


        Double wynik = (a + b) / 2;
        Double poprzedni = Double.POSITIVE_INFINITY;

        Funkcja funkcja = Main.choice(wybor);


        Double wartosc = funkcja.oblicz(wynik);
        if (wartosc == 0) {
            return wynik;
        } else {
            while (Math.abs(wynik - poprzedni) >= epsilon) {
                poprzedni = wynik;
                wartosc = funkcja.oblicz(wynik);
                Double wartoscA = funkcja.oblicz(a);
                if (wartoscA * wartosc < 0) {
                    b = wynik;
                } else {
                    a = wynik;
                }
                wynik = (a + b) / 2;


            }
            return wynik;
        }
    }
}
