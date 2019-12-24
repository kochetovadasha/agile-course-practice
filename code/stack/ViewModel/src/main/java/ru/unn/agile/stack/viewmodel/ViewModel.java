package ru.unn.agile.stack.viewmodel;

import ru.unn.agile.stack.model.Stack;
import javafx.beans.property.*;

import java.util.List;

public class ViewModel {
    private Stack<Double> stackDouble;
    private final StringProperty isStackEmptyInfo = new SimpleStringProperty();
    private final StringProperty stackSize = new SimpleStringProperty();
    private final StringProperty topElement = new SimpleStringProperty();
    private final StringProperty popElement = new SimpleStringProperty();
    private final StringProperty pushElement = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty popButtonState = new SimpleBooleanProperty();

    private StringProperty textLog = new SimpleStringProperty();

    private ILogger logger;

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);

        init();
    }

    private void init() {
        stackDouble = new Stack<Double>();
        isStackEmptyInfo.set(Status.STACK_IS_EMPTY.toString());
        stackSize.set("0");
        topElement.set("None");
        popElement.set("None");
        pushElement.set("");
        status.set(Status.WAITING.toString());
        textLog.set("");
        popButtonState.set(false);
    }

    public List<String> getLogList() {
        return logger.getLog();
    }

    public String getIsStackEmptyInfo() {
        return isStackEmptyInfo.get();
    }

    public StringProperty isStackEmptyInfoProperty() {
        return isStackEmptyInfo;
    }

    public String getStackSize() {
        return stackSize.get();
    }

    public StringProperty stackSizeProperty() {
        return stackSize;
    }

    public String getTopElement() {
        return topElement.get();
    }

    public StringProperty topElementProperty() {
        return topElement;
    }

    public String getPopElement() {
        return popElement.get();
    }

    public StringProperty popElementProperty() {
        return popElement;
    }

    public String getPushElement() {
        return pushElement.get();
    }

    public StringProperty pushElementProperty() {
        return pushElement;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty getStatusProperty() {
        return status;
    }

    public boolean getPopButtonState() {
        return popButtonState.get();
    }

    public BooleanProperty popButtonStateProperty() {
        return popButtonState;
    }

    public void setPushElement(final String inputElement) {
        pushElement.set(inputElement);
    }

    public StringProperty textLogProperty() {
        return textLog;
    }

    public String getTextLog() {
        return textLog.get();
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    private void writeLog(final String message) {
        logger.log(message);
        StringBuilder logMessages = new StringBuilder();
        List<String> logList = getLogList();
        for (String log : logList) {
            logMessages.append(log).append("\n");
        }
        textLog.set(logMessages.toString());
    }


    private void changeStackProperties() {
        int doubleStackSize = stackDouble.size();
        stackSize.set(Integer.toString(doubleStackSize));
        if (stackDouble.isEmpty()) {
            isStackEmptyInfo.set(Status.STACK_IS_EMPTY.toString());
            topElement.set("None");
            popButtonState.set(false);
        } else {
            isStackEmptyInfo.set(Status.STACK_IS_NOT_EMPTY.toString());
            topElement.set(Double.toString(stackDouble.peek()));
            popButtonState.set(true);
        }
    }

    public void pushNewElement() {
        String pushElement = getPushElement();
        try {
            if (pushElement.isEmpty()) {
                status.set(Status.WAITING.toString());
                writeLog("Pushing element is empty");
            } else {
                Double doubleElement = Double.parseDouble(pushElement);
                stackDouble.push(doubleElement);
                status.set(Status.READY.toString());
                changeStackProperties();
                writeLog("Push element " + doubleElement + " into stack."
                        + " Size of stack: " + stackDouble.size());
            }
        } catch (NumberFormatException e) {
            status.set(Status.BAD_FORMAT.toString());
            writeLog("Pushing element " + pushElement + " has invalid format");
        }
    }



    public void popElement() {
        if (!stackDouble.isEmpty()) {
            popElement.set(Double.toString(stackDouble.pop()));
            changeStackProperties();
            writeLog("Pop element " + getPopElement() + " from stack."
                    + " Size of stack: " + stackDouble.size());
        }
    }
}

enum Status {
    WAITING("Waiting for new element"),
    READY("Ready to push new element"),
    BAD_FORMAT("Invalid format of the pushing element"),
    STACK_IS_EMPTY("Stack is empty"),
    STACK_IS_NOT_EMPTY("Stack is not empty");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
