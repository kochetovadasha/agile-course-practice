package ru.unn.agile.vectorsdistancescalculator.model;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class VectorsDistancesCalculator {

    public static double calculateLp(final List<Double> x,
                                     final List<Double> y,
                                     final int power) {
        List<Double> dif = calculateVectorsDifference(x, y);
        return VectorMetrics.calculateLp(dif, power);
    }

    public static double calculateLInf(final List<Double> x, final List<Double> y) {
        List<Double> dif = calculateVectorsDifference(x, y);
        return VectorMetrics.calculateLInf(dif);
    }

    private static List<Double> calculateVectorsDifference(final List<Double> x,
                                                           final List<Double> y) {
        if (x == null || y == null) {
            throw new NullPointerException("Vector(s) can't be null");
        }
        if (x.isEmpty() || y.isEmpty()) {
            throw new NoSuchElementException("Vector(s) can't be empty");
        }
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Vectors must have same dimension");
        }
        List<Double> dif = new ArrayList<>();
        for (int i = 0; i < x.size(); ++i) {
            dif.add(y.get(i) - x.get(i));
        }
        return dif;
    }

    public enum Operation {
        CALCULATE_L1_DISTANCE("Calculate L1 Distance") {
            public double apply(final List<Double> x, final List<Double> y) {
                final int power = 1;
                return VectorsDistancesCalculator.calculateLp(x, y, power);
            }
        },
        CALCULATE_L2_DISTANCE("Calculate L2 Distance") {
            public double apply(final List<Double> x, final List<Double> y) {
                final int power = 2;
                return VectorsDistancesCalculator.calculateLp(x, y, power);
            }
        },

        CALCULATE_L3_DISTANCE("Calculate L3 Distance") {
            public double apply(final List<Double> x, final List<Double> y) {
                final int power = 3;
                return VectorsDistancesCalculator.calculateLp(x, y, power);
            }
        },

        CALCULATE_L4_DISTANCE("Calculate L4 Distance") {
            public double apply(final List<Double> x, final List<Double> y) {
                final int power = 4;
                return VectorsDistancesCalculator.calculateLp(x, y, power);
            }
        },

        CALCULATE_LInf_DISTANCE("Calculate LInf Distance") {
            public double apply(final List<Double> x, final List<Double> y) {
                return VectorsDistancesCalculator.calculateLInf(x, y);
            }
        };

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract double apply(List<Double> x, List<Double> y);
    }

    private VectorsDistancesCalculator() { }

}
