package ru.unn.agile.vectorsdistancescalculator.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;


public class VectorsDistancesCalculatorTests {

    private static final double EPS = 0.01;

    @Test(expected = IllegalArgumentException.class)
    public void calculateLpThrowExceptionIfVectorsHaveDifferentDimensions() {
        List<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        List<Double> y = new ArrayList<>(List.of(1.0));
        int power = 1;
        VectorsDistancesCalculator.calculateLp(x, y, power);
    }

    @Test(expected = NoSuchElementException.class)
    public void calculateLpThrowExceptionIfVectorsEmpty() {
        List<Double> x = new ArrayList<>(List.of(1.0));
        List<Double> y = new ArrayList<>();
        int power = 1;
        VectorsDistancesCalculator.calculateLp(x, y, power);
    }

    @Test(expected = NullPointerException.class)
    public void calculateLpThrowExceptionIfVectorNull() {
        List<Double> x = null;
        List<Double> y = new ArrayList<>();
        int power = 1;
        VectorsDistancesCalculator.calculateLp(x, y, power);
    }

    @Test
    public void canCalculateL1DistancePositiveElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, 2.0));
        List<Double> y = new ArrayList<>(List.of(2.0, 1.0));
        int power = 1;
        double expectedL1 = 2.0;
        double actualL1 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL1, actualL1, EPS);

    }

    @Test
    public void canCalculateL1DistanceMixedElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, -2.0));
        List<Double> y = new ArrayList<>(List.of(-2.0, 1.0));
        int power = 1;
        double expectedL1 = 8.0;
        double actualL1 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL1, actualL1, EPS);
    }

    @Test
    public void canCalculateL2DistancePositiveElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, 2.0));
        List<Double> y = new ArrayList<>(List.of(2.0, 1.0));
        int power = 2;
        double sum = 2.0;
        double expectedL2 = Math.sqrt(sum);
        double actualL2 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL2, actualL2, EPS);

    }

    @Test
    public void canCalculateL2DistanceMixedElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, -2.0));
        List<Double> y = new ArrayList<>(List.of(-2.0, 1.0));
        int power = 2;
        double sum = 34.0;
        double expectedL2 = Math.sqrt(sum);
        double actualL2 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL2, actualL2, EPS);
    }

    @Test
    public void canCalculateL3DistancePositiveElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, 2.0));
        List<Double> y = new ArrayList<>(List.of(2.0, 1.0));
        int power = 3;
        double sum = 2.0;
        double expectedL3 = Math.pow(sum, 1. / 3);
        double actualL3 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL3, actualL3, EPS);

    }

    @Test
    public void canCalculateL3DistanceMixedElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, -2.0));
        List<Double> y = new ArrayList<>(List.of(-2.0, 1.0));
        int power = 3;
        double sum = 152;
        double expectedL3 = Math.pow(sum, 1. / 3);
        double actualL3 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL3, actualL3, EPS);
    }

    @Test
    public void canCalculateL4DistancePositiveElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, 2.0));
        List<Double> y = new ArrayList<>(List.of(2.0, 1.0));
        int power = 4;
        double sum = 2.0;
        double expectedL4 = Math.pow(sum, 1. / 4);
        double actualL4 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL4, actualL4, EPS);

    }

    @Test
    public void canCalculateL4DistanceMixedElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, -2.0));
        List<Double> y = new ArrayList<>(List.of(-2.0, 1.0));
        int power = 4;
        double sum = 706;
        double expectedL4 = Math.pow(sum, 1. / 4);
        double actualL4 = VectorsDistancesCalculator.calculateLp(x, y, power);
        assertEquals(expectedL4, actualL4, EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateLInfThrowExceptionIfVectorsHaveDifferentDimensions() {
        List<Double> x = new ArrayList<>(List.of(1.0, 2.0));
        List<Double> y = new ArrayList<>(List.of(1.0));
        VectorsDistancesCalculator.calculateLInf(x, y);
    }

    @Test(expected = NoSuchElementException.class)
    public void calculateLInfThrowExceptionIfVectorsEmpty() {
        List<Double> x = new ArrayList<>(List.of(1.0));
        List<Double> y = new ArrayList<>();
        VectorsDistancesCalculator.calculateLInf(x, y);
    }

    @Test(expected = NullPointerException.class)
    public void calculateLInfThrowExceptionIfVectorNull() {
        List<Double> x = null;
        List<Double> y = new ArrayList<>();
        VectorsDistancesCalculator.calculateLInf(x, y);
    }

    @Test
    public void canCalculateLInfDistanceWithPositiveElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, 2.0));
        List<Double> y = new ArrayList<>(List.of(2.0, 1.0));
        double expectedLInf = 1.0;
        double actualLInf = VectorsDistancesCalculator.calculateLInf(x, y);
        assertEquals(expectedLInf, actualLInf, EPS);
    }

    @Test
    public void canCalculateLInfDistanceWithMixedElems() {
        List<Double> x = new ArrayList<>(List.of(3.0, -2.0));
        List<Double> y = new ArrayList<>(List.of(-2.0, 1.0));
        double expectedLInf = 5.0;
        double actualLInf = VectorsDistancesCalculator.calculateLInf(x, y);
        assertEquals(expectedLInf, actualLInf, EPS);
    }
}

