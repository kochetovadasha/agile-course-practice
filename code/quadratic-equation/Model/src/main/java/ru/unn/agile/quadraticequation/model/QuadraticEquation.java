package ru.unn.agile.quadraticequation.model;

import ru.unn.agile.ComplexNumber.model.ComplexNumber;

public final class QuadraticEquation {

    private final double determinantCoeff = 4;
    private final double a;
    private final double b;
    private final double c;

    public QuadraticEquation(final double a, final double b, final double c) {
        if ((a == 0) && (b == 0)) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int hashCode() {
        final int shift = 32;

        long temp = Double.doubleToLongBits(a);
        int result = (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(b);
        result = (shift - 1) * result + (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(c);
        result = (shift - 1) * result + (int) (temp ^ (temp >>> shift));
        return result;
    }

    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof QuadraticEquation)) {
            return false;
        }
        return hashCode() == object.hashCode();
    }

    public double getACoeff() {
        return a;
    }

    public double getBCoeff() {
        return b;
    }

    public double getCCoeff() {
        return c;
    }

    public ComplexNumber[] solve() {
        if (a == 0) {
            ComplexNumber[] x = new ComplexNumber[1];
            x[0] = new ComplexNumber(-c / b, 0);
            return x;
        }

        ComplexNumber[] x = new ComplexNumber[2];
        double part = (b * b - determinantCoeff * a * c);
        double realpart = -b / (2 * a);
        double imX1 = 0;
        double imX2 = 0;
        double reX1 = realpart;
        double reX2 = realpart;
        if (part >= 0) {
             reX1 += Math.sqrt(part) / (2 * a);
             reX2 -= Math.sqrt(part) / (2 * a);
        } else {
            imX1 += Math.sqrt(-part) / (2 * a);
            imX2 -= Math.sqrt(-part) / (2 * a);
        }
        x[0] = new ComplexNumber(reX1, imX1);
        x[1] = new ComplexNumber(reX2, imX2);
        return x;
    }

    public String toString() {
        return Formatter.getFormatted(this);
    }

}
