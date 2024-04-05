package edu.num;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    // wyświetlanie pomocnicze
    public static void show(ArrayList<ArrayList<Double>> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        String path = "data/dane";

        ArrayList<ArrayList<Double>> coefficients = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner fileScanner = new Scanner(file);
            int i = 0;
            while (fileScanner.hasNextLine()) {
                coefficients.add(new ArrayList<>());
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\s+");
                for (int j = 0; j < parts.length; j++) {
                    String s = parts[j];
                    coefficients.get(i).add(Double.valueOf(s));
                }
                i++;
            }
            fileScanner.close();
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj wartość epsilon lub wpisz cokolwiek, co nie jest liczbą, aby przyjąć wartość domyślną ε = 0.00000001");
            double epsilon = 0.00000001;
            if (scan.hasNextDouble()) {
                epsilon = scan.nextDouble();
            }

            System.out.println("Otrzymane wyniki:");
            GausseMethod gausseMethod = new GausseMethod(coefficients, epsilon);
            ArrayList<ArrayList<Double>> b = gausseMethod.triangleMatrix();

            try {
                ArrayList<Double> results = gausseMethod.getResults(b);
                for (int j = 0; j < results.size(); j++) {
                    System.out.print("x" + (j+1) + " = " + results.get(j) + "\n");
                }
                //System.out.println(gausseMethod.getResults(b));
            } catch (GausseMethodException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }
    }
}