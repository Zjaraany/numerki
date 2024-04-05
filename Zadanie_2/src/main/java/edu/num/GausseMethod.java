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

    public ArrayList<ArrayList<Double>> triangleMatrix() {

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

            do {
                tempMax = 0;
                indexMax = i;
                for (int max = i; max < copy.size(); max++) {
                    if (tempMax < Math.abs(copy.get(max).get(startCol))) {
                        indexMax = max;
                        tempMax = Math.abs(copy.get(max).get(startCol));
                    }
                }

                if (tempMax < epsilon) {
                    if (startCol < copy.get(i).size() - 1) {
                        startCol++; //warunek granicy
                    } else {
                        return copy;
                    }
                } else {
                    //zamiana wierszy
                    Collections.swap(copy, i, indexMax);
                }

            } while (tempMax < epsilon);

            for (int j = i + 1; j < size; j++) { // kolejne wiersze od ktorych odejmujemy

                double a = copy.get(j).get(startCol) / copy.get(i).get(startCol);
                for (int k = startCol; k < copy.get(i).size(); k++) {
                    double substraction = copy.get(j).get(k) - (a * copy.get(i).get(k));
                    copy.get(j).set(k, substraction);
                }

            }
        }

        return copy;
    }

    public int getRank(ArrayList<ArrayList<Double>> matrix) {
        int size = matrix.size();
        int rank = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (Math.abs(matrix.get(i).get(j)) > epsilon) {
                    rank++;
                    j = size;
                }
            }
        }
        return rank;
    }

    public ArrayList<Double> getResults(ArrayList<ArrayList<Double>> matrix) throws GausseMethodException {
        ArrayList<Double> results = new ArrayList<>();

        ArrayList<ArrayList<Double>> matrixA = new ArrayList<>();

        for (int i = 0; i < matrix.size(); i++) {
            matrixA.add(new ArrayList<>());
            for (int j = 0; j < matrix.getFirst().size() - 1; j++) {
                matrixA.get(i).add(matrix.get(i).get(j));
            }
        }


        int rank = getRank(matrix);

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

        Main.show(matrix);
        if (rank != getRank(matrixA)) {
            throw new GausseMethodException("Sprzeczny");
        } else if (rank == size && rank == matrix.getFirst().size() - 1) {

            for (int i = matrix.size() - 1; i >= 0; i--) {

                double pom = 0;
                for (int j = matrix.size() - 1; j >= matrix.size() - results.size(); j--) {
                    pom = pom + matrix.get(i).get(j) * results.get(matrix.size() - 1 - j);
                }
                //            System.out.println(pom);
                //            System.out.println(matrix.get(i).get(i));
                //            System.out.println(matrix.get(i).get(matrix.get(i).size() - 1));

                double x = (matrix.get(i).getLast() - pom) / matrix.get(i).get(i);
                results.add(x);

            }
            //zrobic reverse results
            Collections.reverse(results);
            return results;
        } else if (rank == size && rank < matrix.getFirst().size() - 1) {
            throw new GausseMethodException("Nieoznaczony");
        } else {
            return null;
        }
    }

}
