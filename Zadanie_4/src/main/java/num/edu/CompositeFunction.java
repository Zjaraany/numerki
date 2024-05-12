package num.edu;

public class CompositeFunction implements MathFunction {
// wzó funkcji: cos|3*x|+8
    @Override
    public Double calculate(Double x) {
        return Math.cos(Math.abs(3 * x)) + 8;
    }
}
