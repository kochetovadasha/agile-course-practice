package ru.unn.agile.statisticscalculation.model;

public class DiscreteRandomVariable {
    private Number[] values;
    private Double[] probabilities;

    public DiscreteRandomVariable(final Number[] values, final Double[] probabilities) {
        DistributionChecker.validate(values, probabilities);
        this.values = values;
        this.probabilities = probabilities;
    }

    public DiscreteRandomVariable(final DiscreteRandomVariable variable) {
        this(variable.getValues(), variable.getProbabilities());
    }

    public Number[] getValues() {
        return this.values;
    }

    public Double[] getProbabilities() {
        return this.probabilities;
    }
}
