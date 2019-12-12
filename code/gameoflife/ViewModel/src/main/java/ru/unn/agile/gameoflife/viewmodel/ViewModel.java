package ru.unn.agile.gameoflife.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private SimpleStringProperty heightField = new SimpleStringProperty();
    private SimpleStringProperty widthField = new SimpleStringProperty();
    private SimpleStringProperty statusText = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        heightField.set("");
        widthField.set("");
        statusText.set(Status.WAITING.toString());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(heightField);
            add(widthField);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public SimpleStringProperty heightFieldProperty() {
        return heightField;
    }

    public SimpleStringProperty widthFieldProperty() {
        return widthField;
    }

    public SimpleStringProperty statusTextProperty() {
        return statusText;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (widthField.get().isEmpty() || heightField.get().isEmpty()) {
                statusText.set(Status.WAITING.toString());
            } else {
                statusText.set(Status.READY.toString());
            }
        }
    }
}

enum Status {
    WAITING("Please enter height and width of the field."),
    READY("Press 'Create' to create the field.");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
