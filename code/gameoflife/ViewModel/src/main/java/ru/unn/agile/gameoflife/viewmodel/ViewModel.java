package ru.unn.agile.gameoflife.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.gameoflife.model.GameOfLife;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private SimpleStringProperty heightField = new SimpleStringProperty();
    private SimpleStringProperty widthField = new SimpleStringProperty();
    private SimpleStringProperty statusText = new SimpleStringProperty();

    private GameOfLife gameOfLife;
    private char[][] gridArray;

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

    public char[][] gridArrayProperty() {
        return gridArray;
    }

    public void createGrid() {
        if (widthField.get().isEmpty() || heightField.get().isEmpty()) {
            statusText.set(Status.WAITING.toString());
        } else {
            gameOfLife = new GameOfLife(Integer.parseInt(widthField.get()),
                                        Integer.parseInt(heightField.get()));

            gridArray = gameOfLife.getGrid().clone();
            statusText.set(Status.INITIALISE.toString());
        }
    }

    private void gameOverCheck() {
        int width = Integer.parseInt(widthField.get());
        int height = Integer.parseInt(heightField.get());

        boolean isGridEmpty = true;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (gridArray[y][x] == '*') {
                    isGridEmpty = false;
                    break;
                }
            }
        }

        if (isGridEmpty) {
            statusText.set(Status.INITIALISE.toString());
        }
    }

    public void changeCellStatus(final int y, final int x) {
        if (gridArray[y][x] == '.') {
            gameOfLife.setCell(y, x);
            gridArray = gameOfLife.getGrid().clone();
            statusText.set(Status.GAMING.toString());
        } else {
            gameOfLife.deleteCell(y, x);
            gameOverCheck();
        }
        gridArray = gameOfLife.getGrid().clone();
    }

    public void getNextStep() {
        gameOfLife.makeTurn();
        gridArray = gameOfLife.getGrid().clone();
        gameOverCheck();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (widthField.get().isEmpty() || heightField.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!widthField.get().isEmpty()) {
                if (Integer.parseInt(widthField.get()) <= 0) {
                    inputStatus = Status.IVALID_INPUT;
                }
            }
            if (!heightField.get().isEmpty()) {
                if (Integer.parseInt(heightField.get()) <= 0) {
                    inputStatus = Status.IVALID_INPUT;
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            statusText.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please enter height and width of the field."),
    READY("Press 'Create' to create the field."),
    INITIALISE("Please select 'live' cells."),
    GAMING("Press 'Next' to make the game step or add live cells."),
    BAD_FORMAT("Bad format."),
    IVALID_INPUT("Height and width should be positive.");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
