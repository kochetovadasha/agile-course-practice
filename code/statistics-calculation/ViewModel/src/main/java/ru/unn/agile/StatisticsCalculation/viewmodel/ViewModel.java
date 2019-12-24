package ru.unn.agile.statisticscalculation.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.statisticscalculation.model.DiscreteRandomVariable;

import java.util.ArrayList;
import java.util.List;

enum InputDataStatus {
    WAITING("Enter data"),
    READY("Input data is correct"),
    BAD_FORMAT("Input data error");

    private final String name;

    InputDataStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

enum DataStatus {
    WAITING("Enter data"),
    READY("Data is correct"),
    BAD_FORMAT("Data normalization error");

    private final String name;

    DataStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

enum OperationStatus {
    WAITING_OPERATION("Choose an operation"),
    WAITING_DATA("Waiting correct data"),
    READY("Press 'Calculate'"),
    WAITING_PARAMETER("Enter parameter"),
    BAD_FORMAT("Error in parameter"),
    SUCCESS("Success");

    private final String name;

    OperationStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

public class ViewModel {
    private final StringProperty newValue = new SimpleStringProperty();
    private final StringProperty newProbability = new SimpleStringProperty();
    private final StringProperty operationParameter = new SimpleStringProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty operationStatus = new SimpleStringProperty();
    private final StringProperty dataStatus = new SimpleStringProperty();
    private final StringProperty inputDataStatus = new SimpleStringProperty();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final BooleanProperty deleteDisabled = new SimpleBooleanProperty();
    private final BooleanProperty updateDisabled = new SimpleBooleanProperty();
    private final BooleanProperty enterParameterVisible = new SimpleBooleanProperty();
    private final BooleanProperty isOperationParameterCorrect = new SimpleBooleanProperty();

    private final IntegerProperty selectedListIndex = new SimpleIntegerProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();

    private final ObservableList<TableElement> listData = FXCollections.observableArrayList();
    private final List<UpdateDataChangeListener> updateDataChangedListeners = new ArrayList<>();
    private static final int NOT_SELECTED = -1;
    private static final String EMPTY = "";
    private DiscreteRandomVariable discreteRandomVariable;

    public ViewModel() {
        setInputFieldsToEmpty();
        operationParameter.set(EMPTY);

        selectedListIndex.set(NOT_SELECTED);

        result.set(EMPTY);
        operationStatus.set(OperationStatus.WAITING_DATA.toString());
        dataStatus.set(DataStatus.WAITING.toString());
        inputDataStatus.set(InputDataStatus.WAITING.toString());
        operation.set(null);

        operationParameter.addListener(
                (observable, oldValue, newValue) -> {
                    operationParameter.set(newValue);
                    try {
                        if (Integer.parseInt(operationParameterProperty().get()) > 0) {
                            isOperationParameterCorrect.set(true);
                        }
                    } catch (NumberFormatException nfe) {
                        isOperationParameterCorrect.set(false);
                    }
                    operationStatus.set(calculateOperationStatus().toString());
                });

        BooleanBinding couldUpdateData = new BooleanBinding() {
            {
                super.bind(newValue, newProbability);
            }

            @Override
            protected boolean computeValue() {
                return calculateInputDataStatus() == InputDataStatus.READY;
            }
        };
        updateDisabled.bind(couldUpdateData.not());

        BooleanBinding couldDelete = new BooleanBinding() {
            {
                super.bind(newValue, newProbability);
            }

            @Override
            protected boolean computeValue() {
                return !(newValue.isEmpty().get() && newProbability.isEmpty().get());
            }
        };
        deleteDisabled.bind(couldDelete.not());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(operationStatus, dataStatus);
            }

            @Override
            protected boolean computeValue() {
                return calculateDataStatus() == DataStatus.READY
                        && calculateOperationStatus() == OperationStatus.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        BooleanBinding couldEnterParameter = new BooleanBinding() {
            {
                super.bind(operation, operationStatus);
            }

            @Override
            protected boolean computeValue() {
                return calculateDataStatus() == DataStatus.READY
                        && (operation.get() == Operation.RAW_MOMENT
                        || operation.get() == Operation.CENTRAL_MOMENT);
            }
        };
        enterParameterVisible.bind(couldEnterParameter);

        final List<StringProperty> fields = new ArrayList<>() {
            {
                add(newValue);
                add(newProbability);
                add(operationParameter);
            }
        };

        for (StringProperty field : fields) {
            final UpdateDataChangeListener listener = new UpdateDataChangeListener();
            field.addListener(listener);
            updateDataChangedListeners.add(listener);
        }
    }

    public StringProperty newValueProperty() {
        return newValue;
    }

    public StringProperty newProbabilityProperty() {
        return newProbability;
    }

    public StringProperty operationParameterProperty() {
        return operationParameter;
    }


    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty operationStatusProperty() {
        return operationStatus;
    }

    public final String getOperationStatus() {
        return operationStatus.get();
    }

    public StringProperty dataStatusProperty() {
        return dataStatus;
    }

    public final String getDataStatus() {
        return dataStatus.get();
    }

    public StringProperty inputDataStatusProperty() {
        return inputDataStatus;
    }

    public final String getInputDataStatus() {
        return inputDataStatus.get();
    }

    public ObservableList<TableElement> getListData() {
        return listData;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    public BooleanProperty deleteDisabledProperty() {
        return deleteDisabled;
    }

    public final boolean isDeleteDisabled() {
        return deleteDisabled.get();
    }

    public BooleanProperty updateDisabledProperty() {
        return updateDisabled;
    }

    public final boolean isUpdateDisabled() {
        return updateDisabled.get();
    }

    public BooleanProperty enterParameterVisibleProperty() {
        return enterParameterVisible;
    }

    public final boolean isEnterParameterVisible() {
        return enterParameterVisible.get();
    }

    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }

    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public void updateTableElement() {
        inputDataStatus.set(calculateInputDataStatus().toString());
        if (calculateInputDataStatus() == InputDataStatus.READY) {
            if (selectedListIndex.get() >= 0) {
                listData.set(selectedListIndex.get(),
                        new TableElement(newValue.getValue(), newProbability.getValue()));
            } else {
                listData.add(new TableElement(newValue.getValue(), newProbability.getValue()));
            }
            setInputFieldsToEmpty();
            inputDataStatus.set(calculateInputDataStatus().toString());
        }
        dataStatus.set(calculateDataStatus().toString());
        operationStatus.set(calculateOperationStatus().toString());
    }

    public void deleteTableElement(final int focusedIndex) {
        if (focusedIndex >= 0 && focusedIndex < listData.size()) {
            listData.remove(focusedIndex);
        }
        setInputFieldsToEmpty();
        inputDataStatus.set(calculateInputDataStatus().toString());
        dataStatus.set(calculateDataStatus().toString());
        operationStatus.set(calculateOperationStatus().toString());
    }

    public void setSelectedElement(final int focusedIndex) {
        selectedListIndex.set(focusedIndex);
        if (selectedListIndex.get() != NOT_SELECTED) {
            newValue.set(listData.get(selectedListIndex.get()).getValue());
            newProbability.set(listData.get(selectedListIndex.get()).getProbability());
            inputDataStatus.set(calculateInputDataStatus().toString());
        }
    }

    public void updateOperation() {
        operationStatus.set(calculateOperationStatus().toString());
    }

    public void calculate() {
        try {
            Double operationResult = operation.get().apply(
                    discreteRandomVariable, operationParameter.get());
            operationStatus.set(OperationStatus.SUCCESS.toString());
            result.set(operationResult.toString());
        } catch (IllegalArgumentException exception) {
            result.set(exception.toString());
        }
    }

    public void resetSelectedElement() {
        selectedListIndex.set(NOT_SELECTED);
        setInputFieldsToEmpty();
        inputDataStatus.set(calculateInputDataStatus().toString());
    }

    private InputDataStatus calculateInputDataStatus() {
        if (newValueProperty().get().isEmpty() || newProbabilityProperty().get().isEmpty()) {
            return InputDataStatus.WAITING;
        }
        try {
            if (!newValueProperty().get().isEmpty()) {
                Double.parseDouble(newValueProperty().get());
            }
            if (!newProbabilityProperty().get().isEmpty()) {
                Double.parseDouble(newProbabilityProperty().get());
                if (Double.parseDouble(newProbabilityProperty().get()) > 1.0) {
                    return InputDataStatus.BAD_FORMAT;
                }
            }
        } catch (NumberFormatException nfe) {
            return InputDataStatus.BAD_FORMAT;
        }

        return InputDataStatus.READY;
    }

    private DataStatus calculateDataStatus() {
        if (listData.isEmpty()) {
            return DataStatus.WAITING;
        }
        Number[] values = createArrayValuesFromList();
        Double[] probabilities = createArrayProbabilitiesFromList();
        try {
            discreteRandomVariable = new DiscreteRandomVariable(values, probabilities);
        } catch (IllegalArgumentException exception) {
            return DataStatus.BAD_FORMAT;
        }

        return DataStatus.READY;
    }

    private OperationStatus calculateOperationStatus() {
        if (calculateDataStatus() != DataStatus.READY) {
            return OperationStatus.WAITING_DATA;
        }
        if (calculateDataStatus() == DataStatus.READY
                && operation.get() == null) {
            return OperationStatus.WAITING_OPERATION;
        }
        if (operation.get() == Operation.EXPECTED_VALUE
                || operation.get() == Operation.DISPERSION) {
            return OperationStatus.READY;
        }
        if ((operation.get() == Operation.CENTRAL_MOMENT
                || operation.get() == Operation.RAW_MOMENT)
                && operationParameter.get().equals(EMPTY)) {
            return OperationStatus.WAITING_PARAMETER;
        }
        if (!isOperationParameterCorrect.get()) {
            return OperationStatus.BAD_FORMAT;
        }
        return OperationStatus.READY;
    }

    private Number[] createArrayValuesFromList() {
        Number[] values = new Number[listData.size()];
        int i = 0;
        for (TableElement element : listData) {
            values[i++] = Double.parseDouble(element.getValue());
        }

        return values;
    }

    private Double[] createArrayProbabilitiesFromList() {
        Double[] probabilities = new Double[listData.size()];
        int i = 0;
        for (TableElement element : listData) {
            probabilities[i++] = Double.parseDouble(element.getProbability());
        }

        return probabilities;
    }

    private void setInputFieldsToEmpty() {
        newValue.set(EMPTY);
        newProbability.set(EMPTY);
    }

    private class UpdateDataChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            inputDataStatus.set(calculateInputDataStatus().toString());
        }
    }
}


