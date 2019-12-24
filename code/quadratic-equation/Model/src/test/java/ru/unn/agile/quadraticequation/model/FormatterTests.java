package ru.unn.agile.quadraticequation.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatterTests {

    @Test
    public void canFormatCoeff() {
        double x = 123456.123;
        StringBuilder s = new StringBuilder();

        String result = Formatter.formatCoeff(x, s).toString();

        assertEquals("123456.12", result);
    }

    @Test
    public void canFormatNegativeCoeff() {
        double x = -9876.543;
        StringBuilder s = new StringBuilder();

        String result = Formatter.formatCoeff(x, s).toString();

        assertEquals(" - 9876.54", result);
    }

    @Test
    public void canFormatZeroCoeff() {
        StringBuilder s = new StringBuilder();

        String result = Formatter.formatCoeff(0, s).toString();

        assertEquals("", result);
    }

    @Test
    public void canConvertToString() {
        QuadraticEquation qe = new QuadraticEquation(2.0, 2.0, 3.0);
        assertEquals("2.00(x^2) + 2.00x + 3.00", qe.toString());
    }

    @Test
    public void canConvertFloatingCoeffsToString() {
        QuadraticEquation qe = new QuadraticEquation(1.11, 2.22, 3.33);
       assertEquals("1.11(x^2) + 2.22x + 3.33", qe.toString());
    }

    @Test
    public void canConvertNegativeCoeffsToString() {
        QuadraticEquation qe = new QuadraticEquation(-1.0, -2.0, -3.0);
        assertEquals(" - (x^2) - 2.00x - 3.00", qe.toString());
    }

    @Test
    public void canConvertCoeffsEqualsToOneToString() {
        QuadraticEquation qe = new QuadraticEquation(1.0, -1.0, 1.0);
        assertEquals("(x^2) - x + 1.00", qe.toString());
    }

    @Test
    public void canConvertWithZeroACoeffToString() {
        QuadraticEquation qe = new QuadraticEquation(0.0, -1.0, 1.0);
        assertEquals(" - x + 1.00", qe.toString());
    }

    @Test
    public void canConvertWithZeroBCoeffToString() {
        QuadraticEquation qe = new QuadraticEquation(1.0, 0.0, 1.0);
        assertEquals("(x^2) + 1.00", qe.toString());
    }

    @Test
    public void canConvertWithZeroCCoeffToString() {
        QuadraticEquation qe = new QuadraticEquation(1.0, -1.0, 0.0);
        assertEquals("(x^2) - x", qe.toString());
    }

    @Test
    public void canConvertWithTwoZeroCoeffsToString() {
        QuadraticEquation qe = new QuadraticEquation(0.0, 1.0, 0.0);
        assertEquals("x", qe.toString());
    }
}
