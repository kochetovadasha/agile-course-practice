package ru.unn.agile.mortgagecalculator.model.calculator;

import ru.unn.agile.mortgagecalculator.model.parameters.MortgageParameters;
import ru.unn.agile.mortgagecalculator.model.report.MortgageMonthReport;
import ru.unn.agile.mortgagecalculator.model.report.MortgageReport;

public class MortgageWithDifferentialPaymentsCalculator extends MortgageCalculator {

    @Override
    public MortgageReport calculate(final MortgageParameters parameters) {
        int months = parameters.getMonthsPeriod();
        double monthPercent = parameters.getMonthPercent();
        double basicPayment = parameters.getAmount() / months;
        double currentAmount = parameters.getAmount();

        MortgageReport report = new MortgageReport(parameters.getAmount());

        double finalAmount = 0;

        while (months > 0) {
            double percentPayment = currentAmount * monthPercent;
            currentAmount -= basicPayment;
            double payment = basicPayment + percentPayment;
            payment += getMonthlyCommission(parameters, currentAmount);
            finalAmount += payment;

            MortgageMonthReport monthReport =
                    new MortgageMonthReport(payment, basicPayment, percentPayment, currentAmount);
            report.add(monthReport);

            months--;
        }

        report.setFinalAmount(round(finalAmount) + getCommission(parameters));

        return report;
    }

}
