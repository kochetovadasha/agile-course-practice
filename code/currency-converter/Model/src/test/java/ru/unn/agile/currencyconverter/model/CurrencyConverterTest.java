package ru.unn.agile.currencyconverter.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyConverterTest {

    private final double delta = 0.01;

    @Test
    public void canConvertEuroToRuble() {
        var expectedRuble = 69.86;
        assertEquals(expectedRuble,
                CurrencyConverter.convert(CurrencyPair.EURO_TO_RUBLE, 1), delta);
    }

    @Test
    public void canConvertRubleToEuro() {
        var expectedEuro = 140;
        assertEquals(expectedEuro,
                CurrencyConverter.convert(CurrencyPair.RUBLE_TO_EURO, 10000), delta);
    }

    @Test
    public void canConvertEuroToDollar() {
        var expectedDollar = 112;
        assertEquals(expectedDollar,
                CurrencyConverter.convert(CurrencyPair.EURO_TO_DOLLAR, 100), delta);
    }

    @Test
    public void canConvertDollarToEuro() {
        var expectedEuro = 8900;
        assertEquals(expectedEuro,
                CurrencyConverter.convert(CurrencyPair.DOLLAR_TO_EURO, 10000), delta);
    }

    @Test
    public void canConvertDollarToRuble() {
        var expectedRuble = 625.5;
        assertEquals(expectedRuble,
                CurrencyConverter.convert(CurrencyPair.DOLLAR_TO_RUBLE, 10), delta);
    }

    @Test
    public void canConvertRubleToDollar() {
        var expectedDollar = 160;
        assertEquals(expectedDollar,
                CurrencyConverter.convert(CurrencyPair.RUBLE_TO_DOLLAR, 10000), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotConvertNegative() {
        var converter = CurrencyConverter.convert(CurrencyPair.EURO_TO_RUBLE, -1);
    }
}
