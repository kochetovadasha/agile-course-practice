package ru.unn.agile.binarysearch.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.binarysearch.model.BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    private final StringProperty arrayInput = new SimpleStringProperty();
    private final StringProperty elementInput = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private boolean arrayCorrect;
    private boolean elementCorrect;
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private int key;
    private BinarySearch binarySearch;

    public ViewModel() {
        arrayInput.set("");
        elementInput.set("");
        status.set("");
        result.set("");
        arrayCorrect = false;
        elementCorrect = false;

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                 add(arrayInput);
                 add(elementInput);
            }
        };
        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty arrayInputProperty() {
        return arrayInput;
    }

    public StringProperty elementInputProperty() {
        return elementInput;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public String getArrayInputProperty() {
        return arrayInput.get();
    }

    public String getElementInputProperty() {
        return elementInput.get();
    }

    public String getStatusProperty() {
        return status.get();
    }

    public String getResultProperty() {
        return result.get();
    }

    public void setArrayInputProperty(final String input) {
        arrayInput.set(input);
    }

    public void setElementInputProperty(final String input) {
        elementInput.set(input);
    }

    public int[] getBinarySearchArray() {
        return binarySearch.getArray();
    }

    public void search() {
        int index = binarySearch.search(key);
        if (index == binarySearch.NOT_FOUND) {
            result.set("Key not found");
        } else if (index == binarySearch.NOT_SORTED) {
            result.set("Array not sorted");
        } else {
            result.set("Found key, index " + Integer.toString(index));
        }
    }

    public Status getStatus() {
        Status status = Status.READY;
        try {
            String[] split = arrayInput.get().split(",");
            int[] arr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                arr[i] = Integer.parseInt(split[i]);
            }
            arrayCorrect = true;
            binarySearch = new BinarySearch(arr);
        } catch (NumberFormatException nfe) {
            status = Status.BAD_ARRAY_FORMAT;
        }
        try {
            key = Integer.parseInt(elementInput.get());
            elementCorrect = true;
        } catch (NumberFormatException nfe) {
            status = Status.BAD_ELEMENT_FORMAT;
        }
        if ((getArrayInputProperty() == "") || (getElementInputProperty() == "")) {
            status = Status.WAITING;
        }

        return status;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getStatus().toString());
        }
    }
}

enum Status {
    WAITING("Waiting for input"),
    READY("Press Search"),
    BAD_ARRAY_FORMAT("Bad array format, please enter \n array of ints, comma separated"),
    BAD_ELEMENT_FORMAT("Bad element format, please enter \n the key to search");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
