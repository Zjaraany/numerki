package edu.num;

import java.util.ArrayList;
import java.util.Collections;

public class GausseMethod {

    private ArrayList<ArrayList<Double>> coefficent;
    private double epsilon;

    GausseMethod (ArrayList<ArrayList<Double>> list, double eps) {
        coefficent = list;
        epsilon = eps;
    }

    // sprowadzanie do macierzy trójkątnej
    public ArrayList<ArrayList<Double>> triangleMatrix() {

        // tworzenie kopii oryginalnej macierzy, żeby móc na niej pracować
        ArrayList<ArrayList<Double>> copy = new ArrayList<>();
        for (ArrayList<Double> row : coefficent) {
            ArrayList<Double> newRow = new ArrayList<>();
            for (Double value : row) {
                newRow.add(value);
            }
            copy.add(newRow);
        }

        int size = copy.size();

        for (int i = 0, startCol = 0; (i < size - 1) && (startCol < copy.get(i).size()); i++, startCol++) {

            double tempMax;
            int indexMax;

            // częściowy wybór elementu głównego
            do {
                tempMax = 0;
                indexMax = i;
                for (int max = i; max < size; max++) {
                    if (tempMax < Math.abs(copy.get(max).get(startCol))) {
                        indexMax = max;
                        tempMax = Math.abs(copy.get(max).get(startCol));
                    }
                }
                // przypadek, gdy w danej kolumnie dla wszystkich wierszy są same zera
                if (tempMax < epsilon) {
                    if (startCol < copy.get(i).size() - 1) {
                        startCol++; //warunek granicy
                    } else {
                        return copy;
                    }
                } else {
                    // zamiana wierszy
                    Collections.swap(copy, i, indexMax);
                }

            } while (tempMax < epsilon);

            // kolejne wiersze od których odejmujemy
            for (int j = i + 1; j < size; j++) {
                double m = copy.get(j).get(startCol) / copy.get(i).get(startCol);
                for (int k = startCol; k < copy.get(i).size(); k++) {
                    double substraction = copy.get(j).get(k) - (m * copy.get(i).get(k));
                    copy.get(j).set(k, substraction);
                }

            }
        }
        return copy;
    }

    // obliczanie rzędu macierzy, żeby wyznaczyć układy nieoznaczone i sprzeczne
    public int getRank(ArrayList<ArrayList<Double>> matrix) {
        int size = matrix.size();
        int rank = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (Math.abs(matrix.get(i).get(j)) > epsilon) {
                    rank++;
                    j = matrix.get(i).size();
                }
            }
        }
        return rank;
    }

    public ArrayList<Double> getResults(ArrayList<ArrayList<Double>> matrix) throws GausseMethodException {
        ArrayList<Double> results = new ArrayList<>();

        // macierz bez kolumny z wyrazami wolnymi
        ArrayList<ArrayList<Double>> matrixA = new ArrayList<>();

        for (int i = 0; i < matrix.size(); i++) {
            matrixA.add(new ArrayList<>());
            for (int j = 0; j < matrix.getFirst().size() - 1; j++) {
                matrixA.get(i).add(matrix.get(i).get(j));
            }
        }

        int rank = getRank(matrix);

        // znajdowanie i usuwanie wierszy złożonych z samych zer
        for (int i = 0; i < matrix.size(); i++) {
            int counter = 0;
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (Math.abs(matrix.get(i).get(j)) < epsilon) {
                    counter++;
                }
            }
            if (counter == matrix.get(i).size()) {
                matrix.remove(i);
                i--;
            }
        }

        int size = matrix.size();

        if (rank != getRank(matrixA)) {
            throw new GausseMethodException("Układ sprzeczny");
        } else if (rank == matrixA.getFirst().size()) {
            // metoda podstawiania w tył
            for (int i = size - 1; i >= 0; i--) {
                double pom = 0;
                for (int j = size - 1; j >= matrix.size() - results.size(); j--) {
                    pom = pom + matrix.get(i).get(j) * results.get(matrix.size() - 1 - j);
                }

                double x = (matrix.get(i).getLast() - pom) / matrix.get(i).get(i);
                results.add(x);
            }

            Collections.reverse(results);
            return results;

        } else { //if (rank < matrixA.getFirst().size()) {
            throw new GausseMethodException("Układ nieoznaczony - rozwiązanie posiada następującą liczbę parametrów: "+(matrixA.getFirst().size() - rank));
        }
    }
}