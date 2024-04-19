package num.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PolynomialHorner implements MathFunction {

    private double[] coefficient;

    PolynomialHorner(double[] coefficient) {
        this.coefficient = coefficient;
    }

    // Dodajemy konstruktor wczytujący współczynniki z pliku
    PolynomialHorner(String filePath) throws FileNotFoundException {
        // Wczytujemy współczynniki z pliku
        readCoefficientsFromFile(filePath);
    }

    // Metoda wczytująca współczynniki z pliku
    private void readCoefficientsFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        // Liczymy liczbę współczynników
        int numCoefficients = 0;
        while (scanner.hasNextDouble()) {
            numCoefficients++;
            scanner.nextDouble();
        }

        // Tworzymy tablicę współczynników
        coefficient = new double[numCoefficients];

        // Resetujemy scanner
        scanner = new Scanner(file);

        // Wczytujemy współczynniki
        for (int i = 0; i < numCoefficients; i++) {
            coefficient[i] = scanner.nextDouble();
        }

        // Zamykamy scanner
        scanner.close();
    }

    @Override
    public Double calculate(Double x) {
        int len = coefficient.length;

        if (len == 1) {
            return coefficient[0];
        } else {
            double result = coefficient[len - 1];

            for (int i = len - 2; i >= 0; i--) {
                result *= x;
                result += coefficient[i];
            }

            return result;
        }
    }
}
