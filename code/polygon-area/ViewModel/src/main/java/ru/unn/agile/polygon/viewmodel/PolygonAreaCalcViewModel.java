package ru.unn.agile.polygon.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.polygon.model.Polygon;
import ru.unn.agile.polygon.model.Point;

import java.util.List;
import java.util.regex.Pattern;

public class PolygonAreaCalcViewModel {
    private Polygon polygon;

    private static final Pattern COORDINATE_INPUT_ALLOWED_SYMBOLS =
            Pattern.compile("^[-+]?[0-9]+\\.?[0-9]*$");
    private final SimpleBooleanProperty addingNewPointDisabled = new SimpleBooleanProperty();

    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty xCoordinate = new SimpleStringProperty();
    private final StringProperty yCoordinate = new SimpleStringProperty();
    private final ObservableList<Point> pointList = FXCollections.observableArrayList();

    private final StringProperty result = new SimpleStringProperty();
    private ILogger logger;

    public PolygonAreaCalcViewModel() {
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

    public PolygonAreaCalcViewModel(final ILogger logger) {
        setLogger(logger);
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

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger can not be null");
        }
        this.logger = logger;
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

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    final class LogMessages {
        public static final String CALCULATE_WAS_PRESSED = "Calculating ";
        public static final String CALCULATION_WAS_SUCCESSFUL = "Calculation completed.";
        public static final String CALCULATION_WAS_UNSUCCESSFUL = "Calculation failed. Incorrect input";
        public static final String EDITING_FINISHED = "Updated input. ";
        public static final String EXPRESSION_IS_VALID = "Expression is valid.";
        public static final String EXPRESSION_IS_INVALID = "Expression is invalid.";

        private LogMessages() { }
    }
}
