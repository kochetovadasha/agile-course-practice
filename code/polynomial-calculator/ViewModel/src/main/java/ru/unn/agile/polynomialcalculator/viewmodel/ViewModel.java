package ru.unn.agile.polynomialcalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.polynomialcalculator.model.Polynomial;

import java.util.regex.Pattern;

public class ViewModel {
    private Polynomial polynomial;

    private final StringProperty degree1 = new SimpleStringProperty();
    private final StringProperty degree2 = new SimpleStringProperty();
    private final StringProperty coeff1 = new SimpleStringProperty();
    private final StringProperty coeff2 = new SimpleStringProperty();

    private static final Pattern COEFF_INPUT_ALLOWED_SYMBOLS =
            Pattern.compile("^[-+]?[0-9]+\\.?[0-9]*$");

    private static final Pattern DEGREE_INPUT_ALLOWED_SYMBOLS =
            Pattern.compile("^[0-9]+$");

    private final ObservableList<Polynomial> pointList = FXCollections.observableArrayList();

    private final StringProperty result = new SimpleStringProperty();

    public ViewModel() {
        clearFormInput();

        BooleanBinding canCalculateBoolBinding = new BooleanBinding() {
            {
                super.bind(degree1, degree2, coeff1, coeff2);
            }
            @Override
            protected boolean computeValue() {
                return (isCoordinatesInputCorrect());
            }
        };
    }

    public boolean isPolynomialInputCorrect() {
        String degree = degree1.get();
        String coeff = coeff1.get();

        return (COEFF_INPUT_ALLOWED_SYMBOLS.matcher(coeff).matches()
                && DEGREE_INPUT_ALLOWED_SYMBOLS.matcher(degree).matches());
    }

    public void addPolynomial() {
        int degree = parseDegree(degree1);
        double coeff = parseCoeff(coeff1);
        pointList.add(new Polynomial(coeff, degree));
        clearFormInput();
    }

    public void calcPolynomial() {
        if (pointList.isEmpty()) {
            return;
        }

        try {
            Polynomial p1 = new Polynomial(pol.get(), degree1.get());
            Polynomial p2 = new Polynomial(re2.get(), degree2.get());

            result.set(operation.get().apply(z1, z2).toString());
            status.set(Status.SUCCESS.toString());

            polynomial = new Polynomial(pointArray);
//            result.setValue(Double.toString(polynomial.getArea()));
        } catch (IllegalArgumentException e) {
            result.setValue(e.getMessage());
        }
    }

    private void clearFormInput() {
        xCoordinate.set("");
        yCoordinate.set("");
    }

    private int parseDegree(final StringProperty degree) {
        if (!isCoordinatesInputCorrect()) {
            throw new IllegalArgumentException("Can't parse degree. Invalid input");
        }
        return Integer.parseInt(degree.get());
    }

    private double parseCoeff(final StringProperty coeff) {
        if (!isCoordinatesInputCorrect()) {
            throw new IllegalArgumentException("Can't parse coeff. Invalid input");
        }
        return Double.parseDouble(coeff.get());
    }

    public StringProperty xProperty() {
        return xCoordinate;
    }
    public StringProperty yProperty() {
        return yCoordinate;
    }
    public ObservableList<Point> getPointList() {
        return pointList;
    }

    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }

    public BooleanProperty addingNewPointDisabledProperty() {
        return addingNewPointDisabled;
    }
    public final boolean isAddingNewPointDisabled() {
        return addingNewPointDisabled.get();
    }
}
