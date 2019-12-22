package ru.unn.agile.currencyconverter.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.currencyconverter.model.CurrencyConverter;
import ru.unn.agile.currencyconverter.model.CurrencyPair;

public class CurrencyConverterViewModel {

    private ObjectProperty<CurrencyPair> currencyPair = new SimpleObjectProperty<>();
    private StringProperty error = new SimpleStringProperty();
    private StringProperty inputCurrency = new SimpleStringProperty();
    private StringProperty outputCurrency = new SimpleStringProperty();
    private BooleanProperty btnDisabled = new SimpleBooleanProperty();

    private final ObjectProperty<ObservableList<CurrencyPair>> currencyPairs =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(CurrencyPair.values()));

    public CurrencyConverterViewModel() {
        inputCurrency.set("");
        error.set("");
        outputCurrency.set("");
        btnDisabled.set(true);
        currencyPair.set(CurrencyPair.RUBLE_TO_DOLLAR);

        inputCurrency.addListener((observable, oldValue, newValue) -> {
            onInput(newValue);
        });

        currencyPair.addListener((observable, oldValue, newValue) -> {
            onTypeChange();
        });
    }

    public StringProperty getInputCurrency() {
        return inputCurrency;
    }

    public StringProperty getOutputCurrency() {
        return outputCurrency;
    }

    public BooleanProperty isConvertButtonDisabled() {
        return btnDisabled;
    }

    public ObjectProperty<CurrencyPair> getCurrencyPair() {
        return currencyPair;
    }

    public ObservableList<CurrencyPair> getCurrencyPairs() {
        return currencyPairs.get();
    }

    public StringProperty getError() {
        return error;
    }

    public void convert() {
        double value = Double.parseDouble(inputCurrency.get());
        value = CurrencyConverter.convert(getCurrencyPair().get(), value);
        outputCurrency.set(String.format("%s", value));
    }

    private void onInput(final String newValue) {
        boolean isDouble = newValue.matches("\\d+(\\.\\d+)?");
        error.set(isDouble || newValue.isEmpty() ? "" : "Incorrect Currency");
        btnDisabled.set(newValue.isEmpty() || !isDouble);
        outputCurrency.set("");
    }

    private void onTypeChange() {
        outputCurrency.set("");
    }

}
