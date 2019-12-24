package ru.unn.agile.depositcalculator.model;

public class Calculator {

    private static final double MIN_INTEREST_RATE_FOR_TAX = 0.1825;
    private static final double DEPOSIT_TAX = 0.35;

    private static final int MAX_PERCENT = 100;
    private static final int MONTHS_IN_YEAR = 12;
    private static final int DAYS_IN_MONTH = 30;
    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_QUARTER = 90;
    private static final int QUARTER_IN_YEAR = 4;

    private double startSum;
    private double percent;
    private double periodCoeff;
    private int periodInDays;
    private int capitalizationCount;
    private double capitalizationCoeff;

    public Calculator setStartSum(final double startSum) {
        if (startSum < 0) {
            throw new IllegalArgumentException("start sum on the deposit should be >= 0");
        }
        this.startSum = startSum;
        return this;
    }

    public Calculator setPercent(final int percent) {
        if (percent < 0 || percent > MAX_PERCENT) {
            throw new IllegalArgumentException("percent should be from 0 to " + MAX_PERCENT);
        }
        this.percent = (double) percent / MAX_PERCENT;
        return this;
    }

    public Calculator setPeriod(final DepositTimeType periodType, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count should be > 0");
        }
        switch (periodType) {
            case YEAR:
                periodCoeff = count;
                periodInDays = count * DAYS_IN_YEAR;
                break;
            case MONTH:
                periodCoeff = (double) count / MONTHS_IN_YEAR;
                periodInDays = count * DAYS_IN_MONTH;
                break;
            case DAY:
                periodCoeff = (double) count / DAYS_IN_YEAR;
                periodInDays = count;
                break;
            default:
                periodCoeff = 0;
                periodInDays = 0;
                break;
        }
        return this;
    }

    public Calculator setCapitalizationPeriod(final CapitalizationPeriod capitalizationPeriod) {
        switch (capitalizationPeriod) {
            case YEAR:
                capitalizationCount = periodInDays / DAYS_IN_YEAR;
                capitalizationCoeff = 1;
                break;
            case QUARTER:
                capitalizationCount = periodInDays / DAYS_IN_QUARTER;
                capitalizationCoeff = 1. / QUARTER_IN_YEAR;
                break;
            case MONTH:
                capitalizationCount = periodInDays / DAYS_IN_MONTH;
                capitalizationCoeff = 1. / MONTHS_IN_YEAR;
                break;
            default:
                capitalizationCoeff = 0;
                capitalizationCount = 0;
                break;
        }
        return this;
    }

    public double calculate() {
        double result = 0.0;
        if (percent > MIN_INTEREST_RATE_FOR_TAX) {
            percent -= percent * DEPOSIT_TAX;
        }

        if (capitalizationCount == 0) {
            result = startSum + startSum * periodCoeff * percent;
        } else {
            int i = 0;
            result = startSum;
            while (i < capitalizationCount) {
                double capitalizationPerPeriod = result * percent * capitalizationCoeff;
                result += capitalizationPerPeriod;
                i++;
            }
        }

        clear();
        return result;
    }

    private void clear() {
        startSum = 0;
        percent = 0.0;
        periodCoeff = 0.0;
        periodInDays = 0;
        capitalizationCount = 0;
        capitalizationCoeff = 0.0;
    }
}
