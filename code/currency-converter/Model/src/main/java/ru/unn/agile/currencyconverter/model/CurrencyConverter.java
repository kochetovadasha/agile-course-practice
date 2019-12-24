package ru.unn.agile.currencyconverter.model;

public final class CurrencyConverter {

    private CurrencyConverter() {
    }

    public static double convert(final CurrencyPair currencyPair, final double currency) {
        if (currency < 0) {
            throw new IllegalArgumentException("Currency should be positive!");
        }
        return currency * currencyPair.getExchange();
    }
}
