package edu.num;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static void show(ArrayList<ArrayList<Double>> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        String sciezka = "data/dane";

        ArrayList<ArrayList<Double>> wspolczynniki = new ArrayList<>();

        try {
            File plik = new File(sciezka);
            Scanner scanner = new Scanner(plik);
            int i = 0;
            while (scanner.hasNextLine()) {
                wspolczynniki.add(new ArrayList<>());
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");
                for (int j = 0; j < parts.length; j++) {

                    String s = parts[j];
                    wspolczynniki.get(i).add(Double.valueOf(s));
                }
                i++;
            }
            scanner.close();
            show(wspolczynniki);
            System.out.println();
            GausseMethod gausseMethod = new GausseMethod(wspolczynniki);
            ArrayList<ArrayList<Double>> b = gausseMethod.triangleMatrix();
            show(b);
            System.out.println(gausseMethod.getResults(b));


        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }
    }
}
