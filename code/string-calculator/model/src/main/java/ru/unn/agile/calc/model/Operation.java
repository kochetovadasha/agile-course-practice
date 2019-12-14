package ru.unn.agile.calc.model;

public enum Operation {

    SUMMARY {
        public double action(final double x, final double y) {
            return x + y;
        }
        public String value() {
            return "+";
        }
    },
    SUBTRACT {
        public double action(final double x, final double y) {
            return x - y;
        }
        public String value() {
            return "-";
        }
    },
    MULTIPLY {
        public double action(final double x, final double y) {
            return x * y;
        }
        public String value() {
            return "*";
        }
    },
    DIVIDE {
        public double action(final double x, final double y) {
            return x / y;
        }
        public String value() {
            return "/";
        }
    };

    public abstract double action(double x, double y);
    public abstract String value();

    public static Operation getOperationBySymbol(final String operationSymbol) {
        for (Operation v : values()) {
            if (v.value().equals(operationSymbol)) {
                return v;
            }
        }
        return null;
    }
}
