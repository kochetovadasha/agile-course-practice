package ru.unn.agile.currencyconverter.model;

public enum CurrencyPair {
    RUBLE_TO_EURO(ExchangeRates.RUBLE_TO_EURO),
    RUBLE_TO_DOLLAR(ExchangeRates.RUBLE_TO_DOLLAR),
    EURO_TO_RUBLE(ExchangeRates.EURO_TO_RUBLE),
    EURO_TO_DOLLAR(ExchangeRates.EURO_TO_DOLLAR),
    DOLLAR_TO_RUBLE(ExchangeRates.DOLLAR_TO_RUBLE),
    DOLLAR_TO_EURO(ExchangeRates.DOLLAR_TO_EURO);

    private final double exchange;

    CurrencyPair(final double exchange) {
        this.exchange = exchange;
    }

    public double getExchange() {
        return exchange;
    }
}
