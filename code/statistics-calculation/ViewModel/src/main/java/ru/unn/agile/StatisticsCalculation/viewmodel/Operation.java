package ru.unn.agile.statisticscalculation.viewmodel;

import ru.unn.agile.statisticscalculation.model.StatisticsCalculation;
import ru.unn.agile.statisticscalculation.model.DiscreteRandomVariable;

public enum Operation {
    EXPECTED_VALUE("Calculate expected value") {
        public Double apply(final DiscreteRandomVariable drv, final String ... params) {
            return StatisticsCalculation.calculateExpectedValue(drv);
        }
    },
    DISPERSION("Calculate dispersion") {
        public Double apply(final DiscreteRandomVariable drv, final String ... params) {
            return StatisticsCalculation.calculateDispersion(drv);
        }
    },
    RAW_MOMENT("Calculate raw moment") {
        public Double apply(final DiscreteRandomVariable drv, final String ... params) {
            return StatisticsCalculation.calculateRawMoment(drv, Integer.parseInt(params[0]));
        }
    },
    CENTRAL_MOMENT("Calculate central moment") {
        public Double apply(final DiscreteRandomVariable drv, final String ... params) {
            return StatisticsCalculation.calculateCentralMoment(drv, Integer.parseInt(params[0]));
        }
    };

    private final String name;
    Operation(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract Double apply(DiscreteRandomVariable drv, String ... params);
}
