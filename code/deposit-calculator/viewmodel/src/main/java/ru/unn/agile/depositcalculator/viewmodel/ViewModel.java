package ru.unn.agile.depositcalculator.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.depositcalculator.model.Calculator;
import ru.unn.agile.depositcalculator.model.CapitalizationPeriod;
import ru.unn.agile.depositcalculator.model.DepositTimeType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ViewModel {

    private final Pattern pattern =
            Pattern.compile("^([0-9]*)(\\.)?([0-9]*)$", Pattern.CASE_INSENSITIVE);

    public static final String VALIDATION_ERROR =
            "Fields should contains only number and values should be more or equal 0";

    public ViewModel() {
        setCapitalization(CapitalizationPeriod.MONTH);
        setPeriod(DepositTimeType.DAY);
        setStartSumProperty("1000");
        setPercentProperty("8");
    }

    // region fields
    private final SimpleStringProperty periodProperty = new SimpleStringProperty();
    private final SimpleStringProperty capitProperty = new SimpleStringProperty();

    private final SimpleStringProperty percentProperty = new SimpleStringProperty();
    private final SimpleStringProperty startSumProperty = new SimpleStringProperty();

    private final SimpleStringProperty resultProperty = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<DepositTimeType>> periods =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(DepositTimeType.values()));

    private final ObjectProperty<ObservableList<CapitalizationPeriod>> capitalizations =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(CapitalizationPeriod.values()));

    private final ObjectProperty<DepositTimeType> period = new SimpleObjectProperty<>();
    private final ObjectProperty<CapitalizationPeriod> capitalization =
            new SimpleObjectProperty<>();
    // endregion

    // region getters/setters

    public ObservableList<DepositTimeType> getPeriods() {
        return periods.get();
    }
    public ObjectProperty<ObservableList<DepositTimeType>> periodsProperty() {
        return periods;
    }
    public void setPeriods(final ObservableList<DepositTimeType> periods) {
        this.periods.set(periods);
    }

    public ObservableList<CapitalizationPeriod> getCapitalizations() {
        return capitalizations.get();
    }
    public ObjectProperty<ObservableList<CapitalizationPeriod>> capitalizationsProperty() {
        return capitalizations;
    }
    public void setCapitalizations(final ObservableList<CapitalizationPeriod> capitalizations) {
        this.capitalizations.set(capitalizations);
    }

    public DepositTimeType getPeriod() {
        return period.get();
    }
    public ObjectProperty<DepositTimeType> periodProperty() {
        return period;
    }
    public void setPeriod(final DepositTimeType period) {
        this.period.set(period);
    }

    public CapitalizationPeriod getCapitalization() {
        return capitalization.get();
    }
    public ObjectProperty<CapitalizationPeriod> capitalizationProperty() {
        return capitalization;
    }
    public void setCapitalization(final CapitalizationPeriod capitalization) {
        this.capitalization.set(capitalization);
    }

    public String getPercentProperty() {
        return percentProperty.get();
    }
    public SimpleStringProperty percentProperty() {
        return percentProperty;
    }
    public void setPercentProperty(final String percentProperty) {
        this.percentProperty.set(percentProperty);
    }

    public String getStartSumProperty() {
        return startSumProperty.get();
    }
    public SimpleStringProperty startSumProperty() {
        return startSumProperty;
    }
    public void setStartSumProperty(final String startSumProperty) {
        this.startSumProperty.set(startSumProperty);
    }

    public String getResultProperty() {
        return resultProperty.get();
    }
    public SimpleStringProperty resultProperty() {
        return resultProperty;
    }
    public void setResultProperty(final String resultProperty) {
        this.resultProperty.set(resultProperty);
    }

    //endregion

    public void calculate() {
        if (getStartSumProperty().isEmpty()
                || getPercentProperty().isEmpty()
                || !getValidationStatus(getStartSumProperty())
                || !getValidationStatus(getPercentProperty())) {
            setResultProperty(VALIDATION_ERROR);
            return;
        }

        Calculator calculator = new Calculator();
        calculator.setPeriod(getPeriod(), 1);
        calculator.setPercent(Integer.parseInt(getPercentProperty()));
        calculator.setStartSum(Double.parseDouble(getStartSumProperty()));
        calculator.setCapitalizationPeriod(getCapitalization());
        double result = calculator.calculate();
        result = getCustomerFormat(result);
        setResultProperty(String.format("%s", result));
    }

    private boolean getValidationStatus(final String value) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private double getCustomerFormat(final double input) {
        BigDecimal bd = new BigDecimal(input);
        return bd.setScale(2, RoundingMode.UP).doubleValue();
    }

}
