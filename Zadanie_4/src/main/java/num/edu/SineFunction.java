package num.edu;

public class SineFunction implements MathFunction {
    @Override
    public Double calculate(Double x) {
        return Math.sin(x);
    }
}
