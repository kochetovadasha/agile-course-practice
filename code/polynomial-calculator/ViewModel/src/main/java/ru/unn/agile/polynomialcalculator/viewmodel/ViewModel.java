package ru.unn.agile.polynomialcalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.polynomialcalculator.model.Polynomial;

public class ViewModel {
    private Polynomial polynomial;

    private final StringProperty dgree1 = new SimpleStringProperty();
    private final StringProperty dgree2 = new SimpleStringProperty();
    private final StringProperty coeff1 = new SimpleStringProperty();
    private final StringProperty coeff2 = new SimpleStringProperty();

    private final ObservableList<Polynomial> pointList = FXCollections.observableArrayList();

    private final StringProperty result = new SimpleStringProperty();

    public ViewModel() {
        clearFormInput();

        BooleanBinding canCalculateBoolBinding = new BooleanBinding() {
            {
                super.bind(dgree1, dgree2, coeff1, coeff2);
            }
            @Override
            protected boolean computeValue() {
                return (isCoordinatesInputCorrect());
            }
        };
    }

    public boolean isCoordinatesInputCorrect() {
        String exprText1 = dgree1.get();
        String exprText2 = dgree2.get();
    }

    public void addPolynomial() {
        double x = parseCoordinate(dgree2);
        double y = parseCoordinate(yCoordinate);
        pointList.add(newPoint);

        clearFormInput();
    }

    public void calcPolynomial() {
        if (pointList.isEmpty()) {
            return;
        }

        try {
            Polynomial p1 = new Polynomial(pol.get(), im1.get());
            Polynomial p2 = new Polynomial(re2.get(), im2.get());

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

    private double parseCoordinate(final StringProperty coordinate) {
        if (!isCoordinatesInputCorrect()) {
            throw new IllegalArgumentException("Can't parse invalid input");
        }
        return Double.parseDouble(coordinate.get());
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
