package num.edu;

public class NewtonCotes {

    public boolean weightCond;

    Double complexSimpson (MathFunction mathFunction, Double a, Double b, Double epsilon) {
        Double sum;
        Double difference;
        Double subintervals = 1.0;
        MathFunction weight = new WeightFunction();

        Double previousSum = Double.POSITIVE_INFINITY;

        do {
            Double sumEven = 0.0;
            Double sumOdd = 0.0;
            subintervals = subintervals * 2;
            Double h = (b - a) / subintervals;
            Integer xAmount = subintervals.intValue() + 1;
            Double[] x = new Double[xAmount];
            Double[] fx = new Double[xAmount];
            for (int i = 0; i < xAmount; i++) {
                x[i] = a + i * h;
                if (weightCond) {
                    fx[i] = mathFunction.calculate(x[i]) * weight.calculate(x[i]);
                } else {
                    fx[i] = mathFunction.calculate(x[i]);
                }
                if ((i % 2 == 0) && (i != 0) && (i != xAmount - 1)) {
                    sumEven = sumEven + 2 * fx[i];
                } else if ((i % 2 != 0) && (i != 0) && (i != xAmount - 1)) {
                    sumOdd = sumOdd + 4 * fx[i];
                }
            }
            sum = (h / 3) * (fx[0] + fx[xAmount - 1] + sumEven + sumOdd);
            difference = Math.abs(sum - previousSum);
            previousSum = sum;
        } while (difference > epsilon);
        return sum;
    }

    Double limForSimpson (MathFunction mathFunction, Double epsilon)
    {
        Double a;
        Double b;
        Double temp;
        Double result;
        result = 0.0;

        //granica  do +1
        a = 0.0;
        b = 0.5;
        do
        {
            temp = complexSimpson(mathFunction, a, b, epsilon);
            result += temp;
            a = b;
            b = b + ((1 - b) *1 / 2);
        } while (Math.abs(temp) > epsilon);

        //granica do -1
        a = -0.5;
        b = 0.0;
        do
        {
            temp = complexSimpson(mathFunction, a, b, epsilon);
            result += temp;
            b = a;
            a = a - ((1 - Math.abs(b)) *1 / 2);
        } while (Math.abs(temp) > epsilon);

        return result;
    }

}
