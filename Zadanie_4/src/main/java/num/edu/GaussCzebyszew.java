package num.edu;

public class GaussCzebyszew {

    Double resultsGC (int nodes, MathFunction mathFunction) {
        Double sum = 0.0;
        Double A = Math.PI/nodes;
        for (int i = 0; i < nodes; i++) {
//            Double x = Math.cos((2*i+1)*Math.PI/(2*nodes+2)); //o dziwo to dokładniej liczy
            Double x = Math.cos((2*i+1)*Math.PI/(2*nodes));
            sum = sum + A * mathFunction.calculate(x);
        }
        return sum;
    }

}
