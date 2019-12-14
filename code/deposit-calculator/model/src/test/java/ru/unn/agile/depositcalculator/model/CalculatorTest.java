package ru.unn.agile.depositcalculator.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private final double delta = 0.01;

    @Test
    public void caclYearDepositWithoutCapitalization() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(100)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 1)
                .calculate();

        assertEquals(110, depositRes, delta);
    }

    @Test
    public void cacl2YearDepositWithoutCapitalization() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(100)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 2)
                .calculate();

        assertEquals(120, depositRes, delta);
    }

    @Test
    public void cacl6MonthDepositWithoutCapitalization() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(200)
                .setPercent(10)
                .setPeriod(DepositTimeType.MONTH, 6)
                .calculate();

        assertEquals(210, depositRes, delta);
    }

    @Test
    public void caclMonthDepositWithoutCapitalization() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(144)
                .setPercent(12)
                .setPeriod(DepositTimeType.MONTH, 1)
                .calculate();

        assertEquals(145.44, depositRes, delta);
    }

    @Test
    public void caclDayDepositWithoutCapitalization() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(7300)
                .setPercent(5)
                .setPeriod(DepositTimeType.DAY, 1)
                .calculate();

        assertEquals(7301, depositRes, delta);
    }

    @Test
    public void caclHalfYearDaysDepositWithoutCapitalization() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(66430)
                .setPercent(10)
                .setPeriod(DepositTimeType.DAY, 182)
                .calculate();

        assertEquals(69742.4, depositRes, delta);
    }

    @Test
    public void caclYearDepositWithCapitalization() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(100)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 1)
                .setCapitalizationPeriod(CapitalizationPeriod.YEAR)
                .calculate();

        assertEquals(110, depositRes, delta);
    }

    @Test
    public void cacl3YearDepositWithCapitalizationPerYear() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 3)
                .setCapitalizationPeriod(CapitalizationPeriod.YEAR)
                .calculate();

        assertEquals(1331, depositRes, delta);
    }

    @Test
    public void cacl3MonthDepositWithCapitalizationPerYear() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(1000)
                .setPercent(15)
                .setPeriod(DepositTimeType.MONTH, 3)
                .setCapitalizationPeriod(CapitalizationPeriod.YEAR)
                .calculate();

        assertEquals(1037.5, depositRes, delta);
    }

    @Test
    public void cacl3MonthDepositWithCapitalizationPerMonth() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(1000)
                .setPercent(15)
                .setPeriod(DepositTimeType.MONTH, 3)
                .setCapitalizationPeriod(CapitalizationPeriod.MONTH)
                .calculate();

        assertEquals(1037.970, depositRes, delta);
    }

    @Test
    public void depositWithCapitalizationPerMonthBetterPerYear() {
        Calculator calc = new Calculator();

        double depositYear = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 3)
                .setCapitalizationPeriod(CapitalizationPeriod.YEAR)
                .calculate();

        double depositMonth = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 3)
                .setCapitalizationPeriod(CapitalizationPeriod.MONTH)
                .calculate();

        assertTrue(depositMonth > depositYear);
    }

    @Test
    public void depositWithCapitalizationPerQuarterBetterPerYear() {
        Calculator calc = new Calculator();

        double depositYear = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 1)
                .setCapitalizationPeriod(CapitalizationPeriod.YEAR)
                .calculate();

        double depositQuarter = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 1)
                .setCapitalizationPeriod(CapitalizationPeriod.QUARTER)
                .calculate();

        assertTrue(depositQuarter >= depositYear);
    }

    @Test
    public void depositWithCapitalizationPerMonthBetterPerQuarter() {
        Calculator calc = new Calculator();

        double depositQuarter = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 1)
                .setCapitalizationPeriod(CapitalizationPeriod.QUARTER)
                .calculate();

        double depositMonth = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 1)
                .setCapitalizationPeriod(CapitalizationPeriod.MONTH)
                .calculate();

        assertTrue(depositMonth >= depositQuarter);
    }

    @Test
    public void depositWithCapitalizationBetterWithoutCapitalization() {
        Calculator calc = new Calculator();

        double depositCapital = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 3)
                .setCapitalizationPeriod(CapitalizationPeriod.YEAR)
                .calculate();

        double depositDefault = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 3)
                .calculate();

        assertTrue(depositCapital > depositDefault);
    }

    @Test
    public void depositWithTheSamePeriodYearMonthAreEqual() {
        Calculator calc = new Calculator();

        double depositYear = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.YEAR, 3)
                .calculate();

        double depositMonth = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.MONTH, 36)
                .calculate();

        assertEquals(depositMonth, depositYear, delta);
    }

    @Test
    public void depositWithTheSamePeriodMonthDayAreEqual() {
        Calculator calc = new Calculator();

        double depositMonth = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.MONTH, 12)
                .calculate();

        double depositYear = calc.setStartSum(1000)
                .setPercent(10)
                .setPeriod(DepositTimeType.DAY, 365)
                .calculate();

        assertEquals(depositMonth, depositYear, delta);
    }

    @Test
    public void cacl3MonthDepositWithTax() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(100)
                .setPercent(50)
                .setPeriod(DepositTimeType.MONTH, 3)
                .calculate();

        assertEquals(108.125, depositRes, delta);
    }

    @Test
    public void caclMonthDepositWithCapitalizationAndTax() {
        Calculator calc = new Calculator();
        double depositRes = calc.setStartSum(100)
                .setPercent(50)
                .setPeriod(DepositTimeType.MONTH, 3)
                .setCapitalizationPeriod(CapitalizationPeriod.MONTH)
                .calculate();

        assertEquals(108.347, depositRes, delta);
    }
}
