package num.edu;

import java.util.Random;
import java.util.Stack;

public class NewtonInterpolation {
    private Double rangeA;
    private Double rangeB;
    private Double nodeX[];
    //    private Double nodeY [] = new Double[nodeX.length];;
    private MathFunction mathFunction;

    NewtonInterpolation(MathFunction fun, Double a, Double b,Double X[]) {
//        to by ewentualnie było w konstruktorze, bo jest w poleceniu: Double a, Double b,
        mathFunction = fun;
        rangeA = a;
        rangeB = b;
        nodeX = X;
    }

    public Double[] getDifferenceQuotients() {
        Integer nodesNumber = nodeX.length;

        Double nodeY[] = new Double[nodesNumber];

        for (int i = 0; i < nodesNumber; i++) {
            nodeY[i] = mathFunction.calculate(nodeX[i]);
        }

        Double differenceQuotients[][] = new Double[nodesNumber][nodesNumber];

        for (int i = 0; i < nodesNumber; i++) {
            differenceQuotients[i][0] = nodeY[i];
        }

        for (int j = 1; j < nodesNumber; j++) {
            for (int i = 0; i < nodesNumber - j; i++) {
                differenceQuotients[i][j] = (differenceQuotients[i + 1][j - 1] - differenceQuotients[i][j - 1]) /
                        (nodeX[i + j] - nodeX[i]);
            }
        }
        return differenceQuotients[0];
    }

    public String writeNewtonPolynomial(Double[] quotients) {

        String s = "W(x) = " + quotients[0] + " ";
        String part = "";

        for (int i = 1; i < quotients.length; i++) {
            String sign;
            if (nodeX[i - 1] > 0) {
                sign = "- ";
            } else {
                sign = "+ ";
            }
            part = part + " * (x " + sign + Math.abs(nodeX[i - 1]) + ") ";
            String signCo;
            if (quotients[i] > 0) {
                signCo = "+ ";
            } else {
                signCo = "- ";
            }
            s = s + signCo + Math.abs(quotients[i]) + part;
        }
        return s;
    }

    public Double calculateFromNewton (Double[] quotients, Double x) {
        Double result;
        Double part = 1.0;
        result = quotients[0];
        for (int i = 1; i < quotients.length; i++) {
            part = part * (x - nodeX[i - 1]);
            result = result + quotients[i] * part;
        }
        return result;
    }

//    public Double [] generateRandomNodes(Integer nodes) {
//        Double partLength = ((rangeB-rangeA)/(nodes+1));
//        Double nodesX [] = new Double[nodes];
//        Random random = new Random();
//        nodesX[0] = rangeA + partLength + random.nextDouble() * partLength - partLength / 2;;
//        for (int i = 1; i < nodes; i++) {
//            nodesX[i] = nodesX[i-1] + partLength + random.nextDouble() * partLength - partLength / 2;;
//        }
//        return nodesX;
//    }



}