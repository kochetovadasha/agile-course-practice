package ru.unn.agile.primenumber.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.primenumber.model.PrimeNumberFinder;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty startElement = new SimpleStringProperty();
    private final StringProperty endElement = new SimpleStringProperty();
    private final StringProperty outputField = new SimpleStringProperty();
    private final BooleanProperty findBtnDisabled = new SimpleBooleanProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private final List<StringProperty> fields = new ArrayList<>() {
        {
            add(startElement);
            add(endElement);
        }
    };

    public BooleanProperty findBtnDisabledProperty() {
        return findBtnDisabled;
    }

    public StringProperty startElemProperty() {
        return startElement;
    }

    public StringProperty endElemProperty() {
        return endElement;
    }

    public StringProperty outputProperty() {
        return outputField;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void findPrimaryNums() {
        if (findBtnDisabled.get()) {
            return;
        }

        try {
            PrimeNumberFinder setOfPrimeNums = new PrimeNumberFinder(
                    Integer.parseInt(fields.get(0).get()), Integer.parseInt(fields.get(1).get()));
            List<Integer> primeNumsList = setOfPrimeNums.findNumbers();
            outputField.set(primeNumsList.toString());
            status.set(Status.SUCCESS.toString());
        } catch (IllegalArgumentException e) {
            outputField.set(e.getMessage());
        }
    }

    public ViewModel() {
        fields.get(0).set("");
        fields.get(1).set("");
        outputField.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldFind = new BooleanBinding() {
            {
                super.bind(fields.get(0), fields.get(1));
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        findBtnDisabled.bind(couldFind.not());

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private Status getInputStatus() {
        Status status = Status.READY;
        if (fields.get(0).get().isEmpty() || fields.get(1).get().isEmpty()) {
            status = Status.WAITING;
        }
        try {
            if (!fields.get(0).get().isEmpty()) {
                Integer.parseInt(fields.get(0).get());
            }
            if (!fields.get(1).get().isEmpty()) {
                Integer.parseInt(fields.get(1).get());
            }
        } catch (NumberFormatException e) {
            status = Status.BAD_FORMAT;
        }
        return status;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            Status currentStatus = getInputStatus();
            status.set(currentStatus.toString());
            if (!currentStatus.equals(Status.SUCCESS)) {
                outputField.set(status.get());
            }
        }
    }
}
