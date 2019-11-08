package ru.unn.agile.mortgagecalculator.model.parameters.commission;

import ru.unn.agile.mortgagecalculator.model.validation.Validator;

public class FixedCommission implements Commission {

    private double value;

    public FixedCommission(final double value) {
        Validator validator = new Validator();
        validator.checkPositiveDouble(value);
        this.value = value;
    }


    @Override
    public double calculate(final double amount) {
        return value;
    }
}
