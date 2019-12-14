package ru.unn.agile.statisticscalculation.model;

public final class StatisticsCalculation {
    public static Double calculateExpectedValue(final DiscreteRandomVariable variable) {
        return calculateRawMoment(variable, 1);
    }

    public static Double calculateDispersion(final DiscreteRandomVariable variable) {
        return calculateCentralMoment(variable, 2);
    }

    public static Double calculateRawMoment(final DiscreteRandomVariable variable,
                                            final Integer order) {
        return calculateMoment(variable, order, 0.0);
    }

    public static Double calculateCentralMoment(final DiscreteRandomVariable variable,
                                                final Integer order) {
        Double expectedValue = calculateExpectedValue(variable);
        return calculateMoment(variable, order, expectedValue);
    }

    private static Double calculateMoment(final DiscreteRandomVariable variable,
                                          final Integer order, final Double offset) {
        MomentOrderChecker.checkOrder(order);
        Number[] values = variable.getValues();
        Double[] probabilities = variable.getProbabilities();
        Double result = 0.0;
        for (int i = 0; i < values.length; i++) {
            result += probabilities[i] * Math.pow((values[i].doubleValue()
                    - offset.doubleValue()), order);
        }
        return result;
    }

    private StatisticsCalculation() { }
}
