package num.edu;

public class NewtonInterpolation {
//    private Double rangeA;
//    private Double rangeB;
    private Double nodeX[];
    //    private Double nodeY [] = new Double[nodeX.length];;
    private MathFunction mathFunction;

    NewtonInterpolation(MathFunction fun, Double X[]) {
//        to by ewentualnie było w konstruktorze, bo jest w poleceniu: Double a, Double b,
        mathFunction = fun;
//        rangeA = a;
//        rangeB = b;
        nodeX = X;
    }

    public Double[] getResults() {
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

    public void writeNewtonPolynomial(Double[] coefficients) {

        String s = "W(x) = " + coefficients[0] + " ";
        String part = "";

        for (int i = 1; i < coefficients.length; i++) {
            String sign;
            if (nodeX[i - 1] > 0) {
                sign = "- ";
            } else {
                sign = "+ ";
            }
            part = part + " * (x " + sign + Math.abs(nodeX[i - 1]) + ") ";
            String signCo;
            if (coefficients[i] > 0) {
                signCo = "+ ";
            } else {
                signCo = "- ";
            }
            s = s + signCo + Math.abs(coefficients[i]) + part;
        }
        System.out.println(s);
    }

    public Double calculateFromNewton (Double[] coefficients, Double x) {
        Double result = 0.0;
        Double part = 1.0;
        result = coefficients[0];
        for (int i = 1; i < coefficients.length; i++) {
            part = part * (x - nodeX[i - 1]);
            result = result + coefficients[i] * part;
        }

        return result;
    }

}