package edu.num;

import java.util.ArrayList;
import java.util.Collections;

public class GausseMethod {

    private ArrayList<ArrayList<Double>> coefficent;

    GausseMethod (ArrayList<ArrayList<Double>> list) {
        coefficent = list;
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

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                double a = copy.get(j).get(i) / copy.get(i).get(i);
                for (int k = i; k < copy.get(i).size(); k++) {
                    double substraction = copy.get(j).get(k) - (a *copy.get(i).get(k));
                    copy.get(j).set(k, substraction);
                }
            }
        }


        return copy;
    }

    public ArrayList<Double> getResults(ArrayList<ArrayList<Double>> matrix) {
        ArrayList<Double> results = new ArrayList<>();
        for (int i = matrix.size() - 1; i >= 0; i--) {
            double pom = 0;
            for (int j = matrix.size() - 1; j >= matrix.size() - results.size(); j--) {
                pom = pom + matrix.get(i).get(j) * results.get(matrix.size() - 1 - j);
            }
            System.out.println(pom);
            System.out.println(matrix.get(i).get(i));
            System.out.println(matrix.get(i).get(matrix.get(i).size() - 1));
            double x = (matrix.get(i).getLast() - pom) / matrix.get(i).get(i);
            results.add(x);

        }
        //zrobic reverse results
        Collections.reverse(results);
        return results;
    }

}
