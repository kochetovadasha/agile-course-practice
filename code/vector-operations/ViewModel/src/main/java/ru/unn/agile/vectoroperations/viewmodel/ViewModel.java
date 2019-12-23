package ru.unn.agile.vectoroperations.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.vectoroperations.model.Vector;
import ru.unn.agile.vectoroperations.model.Vector.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty x0 = new SimpleStringProperty();
    private final StringProperty y0 = new SimpleStringProperty();
    private final StringProperty z0 = new SimpleStringProperty();
    private final StringProperty x1 = new SimpleStringProperty();
    private final StringProperty y1 = new SimpleStringProperty();
    private final StringProperty z1 = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> opsList =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> op = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisablingFlag = new SimpleBooleanProperty();
    private final BooleanProperty additionalVectorFieldDisablingFlag = new SimpleBooleanProperty();

    private final StringProperty fieldResult = new SimpleStringProperty();
    private final StringProperty fieldStatus = new SimpleStringProperty();

    private final List<ChangeListener> valueChangedListeners = new ArrayList<>();

    private ILogger logger;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger param can't be null");
        }
        this.logger = logger;
        x0.set("");
        y0.set("");
        z0.set("");
        x1.set("");
        y1.set("");
        z1.set("");
        op.set(Operation.CALCULATE_NORM);
        fieldResult.set("");
        fieldStatus.set(Status.WAITING.toString());

        final List<StringProperty> fields = new ArrayList<>() { {
            add(x0);
            add(y0);
            add(z0);
            add(x1);
            add(y1);
            add(z1);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
        final OpChangeListner opListner = new OpChangeListner();
        op.addListener(opListner);
        valueChangedListeners.add(opListner);


        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(x0, y0, z0, x1, y1, z1, op);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };

        BooleanBinding needShowAdditionalVectorField = new BooleanBinding() {
            {
                super.bind(op);
            }
            @Override
            protected boolean computeValue() {
                return !Operation.CALCULATE_NORM.equals(op.get())
                        && !Operation.CALCULATE_NORMALIZED_VECTOR.equals(op.get());
            }
        };

        calculationDisablingFlag.bind(couldCalculate.not());
        additionalVectorFieldDisablingFlag.bind(needShowAdditionalVectorField);
    }

    public List<String> getLogMessage() {
        return logger.getLog();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        List<StringProperty> list = new ArrayList<>(List.of(x0, y0, z0));
        if (!Operation.CALCULATE_NORM.equals(op.get())
                && !Operation.CALCULATE_NORMALIZED_VECTOR.equals(op.get())) {
            list.add(x1);
            list.add(y1);
            list.add(z1);
        }
        if (list.stream().anyMatch(elem -> elem.get().isEmpty())) {
            inputStatus = Status.WAITING;
        }
        try {
            for (var property : list) {
                if (!property.get().isEmpty()) {
                    Double.parseDouble(property.get());
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    public StringProperty x0Property() {
        return x0;
    }
    public StringProperty y0Property() {
        return y0;
    }
    public StringProperty z0Property() {
        return z0;
    }
    public StringProperty x1Property() {
        return x1;
    }
    public StringProperty y1Property() {
        return y1;
    }
    public StringProperty z1Property() {
        return z1;
    }
    public ObjectProperty<Operation> opProperty() {
        return op;
    }
    public StringProperty fieldResultProperty() {
        return fieldResult;
    }
    public StringProperty fieldStatusProperty() {
        return fieldStatus;
    }
    public BooleanProperty calculationDisablingFlagProperty() {
        return calculationDisablingFlag;
    }
    public BooleanProperty additionalVectorFieldDisablingFlagProperty() {
        return additionalVectorFieldDisablingFlag;
    }
    public final ObservableList<Operation> getOpsList() {
        return opsList.get();
    }
    public final boolean getCalculationDisablingFlag() {
        return calculationDisablingFlag.get();
    }
    public final boolean getAdditionalVectorFieldDisablingFlag() {
        return additionalVectorFieldDisablingFlag.get();
    }
    public final String getFieldResult() {
        return fieldResult.get();
    }
    public final String getFieldStatus() {
        return fieldStatus.get();
    }

    public void calculate() {
        if (calculationDisablingFlag.get()) {
            return;
        }
        double x1d = Double.parseDouble(x0.get());
        double y1d = Double.parseDouble(y0.get());
        double z1d = Double.parseDouble(z0.get());
        Vector vec1 = new Vector(x1d, y1d, z1d);
        Vector vec2 = null;
        boolean needSecondVector = !Operation.CALCULATE_NORM.equals(op.get())
                                   && !Operation.CALCULATE_NORMALIZED_VECTOR.equals(op.get());
        if (needSecondVector) {
            double x2d = Double.parseDouble(x1.get());
            double y2d = Double.parseDouble(y1.get());
            double z2d = Double.parseDouble(z1.get());
            vec2 = new Vector(x2d, y2d, z2d);
        }


        fieldResult.set(String.valueOf(op.get().apply(vec1, vec2)));
        fieldStatus.set(Status.SUCCESS.toString());
        String logMessage = String.format("Calculate. Args: x0 = %.3f, y0 = %.3f, z0 = %.3f;",
                                          x1d, y1d, z1d);
        if (needSecondVector) {
            logMessage += String.format(" x1 = %.3f, y1 = %.3f, z1 = %.3f;",
                                        vec2.getX(), vec2.getY(), vec2.getZ());
        }
        logMessage += String.format(" Operation: %s", op.get().toString());
        logger.log(logMessage);
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            fieldStatus.set(getInputStatus().toString());
            String logMessage = String.format("Input is updated: (%s, %s, %s); (%s, %s, %s);",
                                              x0.get(), y0.get(), z0.get(),
                                              x1.get(), y1.get(), z1.get());
            logger.log(logMessage);
        }
    }

    private class OpChangeListner implements ChangeListener<Operation> {
        @Override
        public void changed(final ObservableValue<? extends Operation> observable,
                            final Operation oldValue, final Operation newValue) {
            boolean needClearSecondVector =
                    (Operation.CALCULATE_SCALAR_MULT.equals(oldValue)
                    || Operation.CALCULATE_VECTOR_MULT.equals(oldValue))
                    && (Operation.CALCULATE_NORM.equals(newValue)
                    || Operation.CALCULATE_NORMALIZED_VECTOR.equals(newValue));
            if (needClearSecondVector) {
                x1.setValue("");
                y1.setValue("");
                z1.setValue("");
            }
            fieldStatus.set(getInputStatus().toString());
            String logMessage = String.format("Operation is changed to %s", newValue.toString());
            logger.log(logMessage);
        }
    }
}

enum Status {
    WAITING("Enter input data"),
    READY("Press the 'Calculate'"),
    BAD_FORMAT("Bad format, fix it"),
    SUCCESS("Success!");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
