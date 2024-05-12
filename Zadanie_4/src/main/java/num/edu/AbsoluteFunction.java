package num.edu;

public class AbsoluteFunction implements MathFunction {
    @Override
    public Double calculate(Double x) {
        return Math.abs(x);
    }
}
