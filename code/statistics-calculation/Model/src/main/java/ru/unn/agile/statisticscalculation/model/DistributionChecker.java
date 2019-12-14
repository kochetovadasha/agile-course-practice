package ru.unn.agile.statisticscalculation.model;

final class DistributionChecker {
    public static void validate(final Number[] values, final Double[] probabilities) {
        checkValues(values);
        checkProbabilities(probabilities);
        compareArraysSize(values, probabilities);
    }

    private static void checkValues(final Number[] values) {
        checkArraysInitialization(values);
    }

    private static void checkProbabilities(final Double[] probabilities) {
        checkArraysInitialization(probabilities);
        checkEachProbability(probabilities);
        checkProbabilitiesSum(probabilities);
    }

    private static void checkArraysInitialization(final Number[] array)
            throws IllegalArgumentException {
        if (array == null) {
            throw new IllegalArgumentException("Array should not be null!");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Array's length should be more 0!");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                throw new IllegalArgumentException("Array's elements should not be null!");
            }
        }
    }

    private static void compareArraysSize(final Number[] values, final Double[] probabilities)
            throws IllegalArgumentException {
        if (values.length != probabilities.length) {
            throw new IllegalArgumentException("Values and probabilities arrays "
                    + "should have the same size!");
        }
    }

    private static void checkEachProbability(final Double[] probabilities)
            throws IllegalArgumentException {
        Double min = 0.0;
        Double max = 1.0;
        for (int i = 0; i < probabilities.length; i++) {
            if (probabilities[i] > max || probabilities[i] < min) {
                throw new IllegalArgumentException("Probabilities' elements "
                        + "should be more than 0.0 and less than 1.0!");
            }
        }
    }

    private static void checkProbabilitiesSum(final Double[] probabilities)
            throws IllegalArgumentException {
        double sum = 0.0;
        final double expectedSum = 1.0;
        final double delta = 0.001;

        for (int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
        }
        if (Math.abs(expectedSum - sum) > delta) {
            throw new IllegalArgumentException("Probabilities sum should be equal 1.0!");
        }
    }

    private DistributionChecker() { }
}
