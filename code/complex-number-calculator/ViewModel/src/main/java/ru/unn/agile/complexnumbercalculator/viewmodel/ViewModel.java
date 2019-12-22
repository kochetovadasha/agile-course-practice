package ru.unn.agile.complexnumbercalculator.viewmodel;

import ru.unn.agile.complexnumbercalculator.model.ComplexNumber;
import ru.unn.agile.complexnumbercalculator.model.ComplexNumberCalculator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ViewModel {
    private Operations operations;
    private String result;
    private String firstRe;
    private String firstIm;
    private String secondRe;
    private String secondIm;
    private String degree;
    private String errorText;

    private boolean isCalculateButtonEnabled;
    private boolean isDegreeVisible;
    private boolean isErrorMessageDisplayed;
    private boolean isSecondNumberVisible;

    private String patternInput = "-?(\\d+)(\\.(\\d+))?";
    private String patternZero = "-?0(\\.0)?";
    private String patternDegreePow = "-?(\\d+)";
    private String patternDegreeRoot = "(\\d+)";

    private ILogger logger;

    private static final int ENTER = 10;

    public enum Operations {
        ADD("Сложить"),
        SUBTRACT("Вычесть"),
        MULTIPLY("Умножить"),
        DIVIDE("Разделить"),
        POW("Возвести в степень"),
        ROOT("Извлечь корень"),
        CONJUGATION("Найти сопряженное");
        private final String name;

        Operations(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public final class LogMessages {
        public static final String OPERATION_WAS_CHANGED = "Operation was changed from ";
        public static final String CALCULATE_WAS_PRESSED = "Calculate. ";

        private LogMessages() { }
    }

    public ViewModel(final ILogger logger) throws IllegalArgumentException {
        result = "";
        firstRe = "";
        firstIm = "";
        secondRe = "";
        secondIm = "";
        degree = "";
        operations = operations.ADD;
        errorText = "";
        isCalculateButtonEnabled = false;
        isDegreeVisible = false;
        isErrorMessageDisplayed = false;
        isSecondNumberVisible = true;

        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;

    }

    public String getResult() {
        return result;
    }

    public String getFirstRe() {
        return firstRe;
    }

    public void setFirstRe(final String firstRe) {
        this.firstRe = firstRe;
    }

    public String getFirstIm() {
        return firstIm;
    }

    public void setFirstIm(final String firstIm) {

        this.firstIm = firstIm;
    }

    public String getSecondRe() {
        return secondRe;
    }

    public void setSecondRe(final String secondRe) {
        this.secondRe = secondRe;
    }

    public String getSecondIm() {
        return secondIm;
    }

    public void setSecondIm(final String secondIm) {
        this.secondIm = secondIm;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(final String degree) {
        this.degree = degree;
    }

    public Operations getOperations() {
        return operations;
    }

    public void setOperations(final Operations operation) {
        logger.addToLog(getChangeOperationLogMessage(operation));
        this.result = "";
        this.operations = operation;
    }

    public String getError() {
        return errorText;
    }

    private void setError() {
        if (isErrorMessageDisplayed) {
            errorText = "Ошибка: нужно ввести"
                    + " корректные значения!";
        } else {
            errorText = "";
        }
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public boolean isDegreeVisible() {
        return isDegreeVisible;
    }

    public boolean isErrorMessageDisplayed() {
        return isErrorMessageDisplayed;
    }

    public boolean isSecondNumberVisible() {
        return isSecondNumberVisible;
    }

    public void processEnterPress(final int keyCode) {
        if (keyCode == ENTER) {
            calculate();
        }
    }

    public void calculate() {
        processInput();
        if (isCalculateButtonEnabled()) {
            result = "";
            if (isFirstGroupOperation(getOperations())) {
                ComplexNumber z1 = ComplexNumber.createAlgebraicForm(
                        Double.parseDouble(getFirstRe()),
                        Double.parseDouble(getFirstIm()));
                ComplexNumber z2 = ComplexNumber.createAlgebraicForm(
                        Double.parseDouble(getSecondRe()),
                        Double.parseDouble(getSecondIm()));
                switch (getOperations()) {
                    case ADD:
                        result = ComplexNumberCalculator.add(z1, z2).toString();
                        break;
                    case SUBTRACT:
                        result = ComplexNumberCalculator.subtract(z1, z2).toString();
                        break;
                    case MULTIPLY:
                        result = ComplexNumberCalculator.multiply(z1, z2).toString();
                        break;
                    case DIVIDE:
                        result = ComplexNumberCalculator.divide(z1, z2).toString();
                        break;
                }
                logger.addToLog(getCalculateLogMessageWithBinaryOperation());
            } else if (isSecondGroupOperation(getOperations())) {
                ComplexNumber z = ComplexNumber.createAlgebraicForm(
                        Double.parseDouble(getFirstRe()),
                        Double.parseDouble(getFirstIm()));
                if (getOperations().equals(Operations.POW)) {
                    result = ComplexNumberCalculator.pow(
                            z, Integer.parseInt(getDegree())).toString();
                } else if (getOperations().equals(Operations.ROOT)) {
                    ComplexNumber[] resultList =
                            ComplexNumberCalculator.extractRoot(z, Integer.parseInt(getDegree()));
                    for (int i = 0; i < resultList.length; i++) {
                        result += resultList[i].toString() + "; ";
                    }
                }
            } else if (getOperations().equals(Operations.CONJUGATION)) {
                ComplexNumber z = ComplexNumber.createAlgebraicForm(
                        Double.parseDouble(getFirstRe()),
                        Double.parseDouble(getFirstIm()));
                result = ComplexNumberCalculator.conjugation(z).toString();
                logger.addToLog(getCalculateLogMessageWithUnaryOperation());
            }
        }

    }

    private void hideDegree() {
        if (isFirstGroupOperation(getOperations())
                || getOperations().equals(Operations.CONJUGATION)) {
            isDegreeVisible = false;
        } else if (isSecondGroupOperation(getOperations())) {
            isDegreeVisible = true;
        }
    }

    private void hideSecondNumber() {
        if (isSecondGroupOperation(getOperations())
                || getOperations().equals(Operations.CONJUGATION)) {
            isSecondNumberVisible = false;
        } else if (isFirstGroupOperation(getOperations())) {
            isSecondNumberVisible = true;
        }
    }

    public void hideUnnecessaryFields() {
        hideDegree();
        hideSecondNumber();
    }

    private boolean matchInput(final String line, final String pattern) {
        boolean result = false;
        Pattern p  = Pattern.compile(pattern);
        if (!line.isEmpty()) {
            Matcher m = p.matcher(line);
            result = m.matches();
        }
        return result;
    }

    private boolean isFirstGroupOperation(final Operations operations) {
        return operations.equals(Operations.ADD)
                || operations.equals(Operations.SUBTRACT)
                || operations.equals(Operations.MULTIPLY)
                || operations.equals(Operations.DIVIDE);
    }

    private boolean isSecondGroupOperation(final Operations operations) {
        return operations.equals(Operations.POW) || operations.equals(Operations.ROOT);
    }

    private boolean isCorrectInputForFirstGroupOperation() {
        if (isFirstGroupOperation(getOperations())) {
            boolean matchRe1 = matchInput(getFirstRe(), patternInput);
            boolean matchIm1 = matchInput(getFirstIm(), patternInput);
            boolean matchRe2 = matchInput(getSecondRe(), patternInput);
            boolean matchIm2 = matchInput(getSecondIm(), patternInput);
            if (getOperations().equals(Operations.DIVIDE)) {
                boolean isRe2Zero =  matchInput(getSecondRe(), patternZero);
                boolean isIm2Zero = matchInput(getSecondIm(), patternZero);
                if (isRe2Zero && isIm2Zero) {
                    matchRe2 = false;
                    matchIm2 = false;
                }
            }
            if (matchRe1 && matchIm1 && matchRe2 && matchIm2) {
                return true;
            }
        }
        return false;
    }

    private boolean isCorrectInputForSecondGroupOperation() {
        if (isSecondGroupOperation(getOperations())) {
            boolean matchRe1 = matchInput(getFirstRe(), patternInput);
            boolean matchIm1 = matchInput(getFirstIm(), patternInput);
            boolean matchDegree = false;
            if (getOperations().equals(Operations.ROOT)) {
                matchDegree = matchInput(getDegree(), patternDegreeRoot);
            } else {
                matchDegree = matchInput(getDegree(), patternDegreePow);
            }
            if (matchRe1 && matchIm1 && matchDegree) {
                return true;
            }
        }
        return false;
    }

    private boolean isCorrectInputForConjugationOperation() {
        if (getOperations().equals(Operations.CONJUGATION)) {
            boolean matchRe1 = matchInput(getFirstRe(), patternInput);
            boolean matchIm1 = matchInput(getFirstIm(), patternInput);
            if (matchRe1 && matchIm1) {
                return true;
            }
        }
        return false;
    }

    public void processInput() {
        boolean validation1 = isCorrectInputForFirstGroupOperation();
        boolean validation2 = isCorrectInputForSecondGroupOperation();
        boolean validation3 = isCorrectInputForConjugationOperation();
        if (validation1 || validation2 || validation3) {
            isCalculateButtonEnabled = true;
            isErrorMessageDisplayed = false;
        } else {
            isCalculateButtonEnabled = false;
            isErrorMessageDisplayed = true;
        }
        setError();
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private String getChangeOperationLogMessage(Operations operation) {
        return LogMessages.OPERATION_WAS_CHANGED + operations.toString() + " to " + operation.toString();
    }

    private String getCalculateLogMessageWithBinaryOperation() {
        return LogMessages.CALCULATE_WAS_PRESSED + "Arguments"
                + ": Re1 = " + firstRe
                + "; Im1 = " + firstIm
                + "; Re2 = " + secondRe
                + "; Im2 = " + secondIm
                + "."
                + " Operation: " + operations.toString() + ".";
    }

    private String getCalculateLogMessageWithUnaryOperation() {
        return LogMessages.CALCULATE_WAS_PRESSED + "Arguments"
                + ": Re1 = " + firstRe
                + "; Im1 = " + firstIm
                + "."
                + " Operation: " + operations.toString() + ".";
    }
}
