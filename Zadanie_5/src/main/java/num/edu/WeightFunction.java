package num.edu;

public class WeightFunction implements MathFunction {

    @Override
    public Double calculate(Double x) {
        return 1/Math.sqrt(1 - x*x);
    }
}
