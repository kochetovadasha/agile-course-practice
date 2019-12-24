package ru.unn.agile.polynomialcalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.polynomialcalculator.model.Polynomial;

import java.util.regex.Pattern;

public class ViewModel {
    private final StringProperty degree1 = new SimpleStringProperty();
    private final StringProperty coeff1 = new SimpleStringProperty();

    private static final Pattern COEFF_INPUT_ALLOWED_SYMBOLS =
            Pattern.compile("^[-+]?[0-9]+\\.?[0-9]*$");

    private static final Pattern DEGREE_INPUT_ALLOWED_SYMBOLS =
            Pattern.compile("^[0-9]+$");

    private ListProperty<String> polynomialsListProperty = new SimpleListProperty<>();

    private final ObservableList<Polynomial> polynomialsList = FXCollections.observableArrayList();

    private final StringProperty result = new SimpleStringProperty();

    public ListProperty<String> polynomialsProperty() {
        return polynomialsListProperty;
    }

    public boolean isPolynomialInputCorrect() {
        String degree = degree1.get();
        String coeff = coeff1.get();

        return (COEFF_INPUT_ALLOWED_SYMBOLS.matcher(coeff).matches()
                && DEGREE_INPUT_ALLOWED_SYMBOLS.matcher(degree).matches());
    }

    public ViewModel() {
        polynomialsListProperty.set(FXCollections.observableArrayList());
        clearFormInput();
        BooleanBinding canCalculateBoolBinding = new BooleanBinding() {
            {
                super.bind(degree1, coeff1);
            }
            @Override
            protected boolean computeValue() {
                return (isPolynomialInputCorrect());
            }
        };
    }

    public void addPolynomial() {
        var polynomial = parsePolynomial(coeff1, degree1);
        polynomialsList.add(polynomial);
        polynomialsListProperty.add(polynomial.toString());
        clearFormInput();
    }

    public void calcPolynomialAdd() {
        if (polynomialsList.isEmpty()) {
            return;
        }
        try {
            Polynomial polySum = new Polynomial(0, 0);
            for (Polynomial poly : polynomialsList)  {
                polySum = polySum.plus(poly);
            }
            result.set(polySum.toString());
        } catch (IllegalArgumentException e) {
            result.setValue(e.getMessage());
        }
    }

    private void clearFormInput() {
        degree1.set("");
        coeff1.set("");
    }

    private Polynomial parsePolynomial(final StringProperty coeff, final StringProperty degree) {
        if (!isPolynomialInputCorrect()) {
            throw new IllegalArgumentException("Can't parse polynomial. Invalid input");
        }
        return new Polynomial(Double.parseDouble(coeff.get()), Integer.parseInt(degree.get()));
    }

    public StringProperty degreeProperty() {
        return degree1;
    }
    public StringProperty coeffProperty() {
        return coeff1;
    }
    public ObservableList<Polynomial> getPolynomialsList() {
        return polynomialsList;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }

}
