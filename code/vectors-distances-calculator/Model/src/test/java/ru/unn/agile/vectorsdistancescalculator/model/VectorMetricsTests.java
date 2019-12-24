package ru.unn.agile.vectorsdistancescalculator.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class VectorMetricsTests {

    private static final double EPS = 0.01;

    @Test
    public void canCalculateL1DistanceOneDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(1.0));
        double expectedL1 = 1.0;
        int power = 1;
        double actualL1 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL1, actualL1, EPS);
    }

    @Test
    public void canCalculateL1DistanceTwoDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(1.1, 2.2));
        double expectedL1 = 3.3f;
        int power = 1;
        double actualL1 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL1, actualL1, EPS);
    }

    @Test
    public void canCalculateL1DistanceThreeDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(1.1, 2.2, 3.3));
        double expectedL1 = 6.6;
        int power = 1;
        double actualL1 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL1, actualL1, EPS);
    }

    @Test
    public void canCalculateL1DistanceWithNegativeValues() {
        List<Double> vector = new ArrayList<>(List.of(-1.1, 2.2, -3.3));
        double expectedL1 = 6.6;
        int power = 1;
        double actualL1 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL1, actualL1, EPS);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionCalculateLpDistanceEmptyVector() {
        List<Double> vector = new ArrayList<>();
        int power = 1;
        VectorMetrics.calculateLp(vector, power);
    }

    @Test(expected = NullPointerException.class)
    public void throwNPECalculateLpDistanceNullVector() {
        List<Double> vector = null;
        int power = 1;
        VectorMetrics.calculateLp(vector, power);
    }

    @Test
    public void canCalculateL2DistanceOneDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0));
        double expectedL2 = 2.0;
        int power = 2;
        double actualL2 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL2, actualL2, EPS);
    }

    @Test
    public void canCalculateL2DistanceTwoDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0, 3.0));
        double sumSquared = 13.0;
        int power = 2;
        double expectedL2 = Math.sqrt(sumSquared);
        double actualL2 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL2, actualL2, EPS);
    }

    @Test
    public void canCalculateL2DistanceThreeDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0, 3.0, 4.0));
        double sumSquared = 29;
        int power = 2;
        double expectedL2 = Math.sqrt(sumSquared);
        double actualL2 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL2, actualL2, EPS);
    }

    @Test
    public void canCalculateL2DistanceWithNegativeValues() {
        List<Double> vector = new ArrayList<>(List.of(-1.0, 3.0, -4.0));
        double sumSquared = 26;
        int power = 2;
        double expectedL2 = Math.sqrt(sumSquared);
        double actualL2 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL2, actualL2, EPS);
    }


    @Test
    public void canCalculateL3DistanceOneDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0));
        double expectedL3 = 2.0;
        int power = 3;
        double actualL3 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL3, actualL3, EPS);
    }

    @Test
    public void canCalculateL3DistanceTwoDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0, 3.0));
        double sum = 35.0;
        int power = 3;
        double expectedL3 = Math.pow(sum, 1. / 3);
        double actualL3 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL3, actualL3, EPS);
    }

    @Test
    public void canCalculateL3DistanceThreeDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0, 3.0, 4.0));
        double sum = 99;
        int power = 3;
        double expectedL3 = Math.pow(sum, 1. / 3);
        double actualL3 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL3, actualL3, EPS);
    }

    @Test
    public void canCalculateL3DistanceWithNegativeValues() {
        List<Double> vector = new ArrayList<>(List.of(-1.0, 3.0, -4.0));
        double sum = 92;
        int power = 3;
        double expectedL3 = Math.pow(sum, 1. / 3);
        double actualL3 = VectorMetrics.calculateLp(vector, power);
        assertEquals(expectedL3, actualL3, EPS);
    }

    @Test
    public void canCalculateLInfDistanceOneDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0));
        double expectedLInf = 2.0;
        double actualLInf = VectorMetrics.calculateLInf(vector);
        assertEquals(expectedLInf, actualLInf, EPS);
    }

    @Test
    public void canCalculateLInfDistanceTwoDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0, 3.0));
        double expectedLInf = 3.0;
        double actualLInf = VectorMetrics.calculateLInf(vector);
        assertEquals(expectedLInf, actualLInf, EPS);
    }

    @Test
    public void canCalculateLInfDistanceThreeDimensionalVector() {
        List<Double> vector = new ArrayList<>(List.of(2.0, 3.0, 4.0));
        double expectedLInf = 4.0;
        double actualLInf = VectorMetrics.calculateLInf(vector);
        assertEquals(expectedLInf, actualLInf, EPS);
    }

    @Test
    public void canCalculateLInfDistanceWithNegativeValues() {
        List<Double> vector = new ArrayList<>(List.of(-1.0, 3.0, -4.0));
        double expectedLInf = 4.0;
        double actualLInf = VectorMetrics.calculateLInf(vector);
        assertEquals(expectedLInf, actualLInf, EPS);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionCalculateLInfDistanceEmptyVector() {
        List<Double> vector = new ArrayList<>();
        VectorMetrics.calculateLInf(vector);
    }

    @Test(expected = NullPointerException.class)
    public void throwNPECalculateLInfDistanceNullVector() {
        List<Double> vector = null;
        VectorMetrics.calculateLInf(vector);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIAECalculateLpPowerZero() {
        List<Double> vector = new ArrayList<>(List.of(2.0, 3.0, 4.0));
        int power = 0;
        VectorMetrics.calculateLp(vector, power);
    }

    @Test
    public void canCorrectlyCalculateLInfWithZeroFilledVectors() {
        List<Double> x = new ArrayList<>(List.of(0.0, 0.0, 0.0));
        double expectedLInf = 0.0;
        double actualLInf = VectorMetrics.calculateLInf(x);
        assertEquals(expectedLInf, actualLInf, EPS);
    }
}
