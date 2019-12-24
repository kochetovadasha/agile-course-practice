package ru.unn.agile.polygon.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.polygon.model.Polygon;
import ru.unn.agile.polygon.model.Point;

import java.util.regex.Pattern;

public class ViewModel {
    private Polygon polygon;

    private static final Pattern COORDINATE_INPUT_ALLOWED_SYMBOLS =
            Pattern.compile("^[-+]?[0-9]+\\.?[0-9]*$");
    private final SimpleBooleanProperty addingNewPointDisabled = new SimpleBooleanProperty();

    private final StringProperty xCoordinate = new SimpleStringProperty();
    private final StringProperty yCoordinate = new SimpleStringProperty();
    private final ObservableList<Point> pointList = FXCollections.observableArrayList();

    private final StringProperty result = new SimpleStringProperty();

    public ViewModel() {
        clearFormInput();

        BooleanBinding canCalculateBoolBinding = new BooleanBinding() {
            {
                super.bind(xCoordinate, yCoordinate);
            }
            @Override
            protected boolean computeValue() {
                return (isCoordinatesInputCorrect());
            }
        };
        addingNewPointDisabled.bind(canCalculateBoolBinding.not());
    }

    public boolean isCoordinatesInputCorrect() {
        String exprText1 = xCoordinate.get();
        String exprText2 = yCoordinate.get();
        return (COORDINATE_INPUT_ALLOWED_SYMBOLS.matcher(exprText1).matches()
                && COORDINATE_INPUT_ALLOWED_SYMBOLS.matcher(exprText2).matches());
    }

    public void addPoint() {
        double x = parseCoordinate(xCoordinate);
        double y = parseCoordinate(yCoordinate);
        Point newPoint = new Point(x, y);
        pointList.add(newPoint);

        clearFormInput();
    }

    public void calcArea() {
        if (pointList.isEmpty()) {
            return;
        }

        Point[] pointArray = pointList.toArray(Point[]::new);

        try {
            polygon = new Polygon(pointArray);
            result.setValue(Double.toString(polygon.getArea()));
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
