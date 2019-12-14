package ru.unn.agile.vectorsdistancescalculator.model;

import java.util.List;
import java.util.NoSuchElementException;

public final class VectorMetrics {

    public static double calculateLp(final List<Double> vector, final int power)
            throws IllegalArgumentException {
        if (power < 1) {
            throw new IllegalArgumentException("Power can't be lower than one");
        }
        return Math.pow(vector.stream()
                     .map(Math::abs)
                     .map(x -> Math.pow(x, power))
                     .reduce((x, y) -> x + y)
                     .get(), 1. / power);
    }

    public static double calculateLInf(final List<Double> vector)
            throws NoSuchElementException {
        if (vector.isEmpty()) {
            throw new NoSuchElementException("Vector can't be empty");
        }
        return vector.stream()
                     .map(Math::abs)
                     .reduce(Double.NEGATIVE_INFINITY, (x, y) -> x < y ? y : x);
    }

    private VectorMetrics() { }
}
