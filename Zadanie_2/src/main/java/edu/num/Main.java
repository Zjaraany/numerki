package edu.num;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sciezka = "data/dane";

        double[][] wspolczynniki;

        try {
            File plik = new File(sciezka);
            Scanner scanner = new Scanner(plik);

            while (scanner.hasNextDouble()) {
                String slowo = scanner.next();
                System.out.println(slowo);
                // Tutaj możesz przetwarzać odczytane słowo
            }
            scanner.close();


        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }
    }
}
