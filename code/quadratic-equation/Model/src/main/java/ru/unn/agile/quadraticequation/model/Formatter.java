package ru.unn.agile.quadraticequation.model;

public final class Formatter {

    private Formatter() { }

    public static String getFormatted(final QuadraticEquation qe) {
        StringBuilder formattedString = new StringBuilder();
        double a = qe.getACoeff();
        double b = qe.getBCoeff();
        double c = qe.getCCoeff();

        String cString = String.format("%.2f", Math.abs(c));
        cString = cString.replace(",", ".");

        formattedString = formatCoeff(a, formattedString);
        formattedString.append(a != 0 ? "(x^2)" : "");
        if (a != 0 && b > 0) {
            formattedString.append(" + ");
        }

        formattedString = formatCoeff(b, formattedString);
        formattedString.append(b != 0 ? "x" : "");
        if ((b != 0 || a != 0) && c > 0) {
            formattedString.append(" + ");
        }

        if (c != 0) {
            if (c < 0) {
                formattedString.append(" - ");
            }
            formattedString.append(cString);
        }

        return formattedString.toString();
    }

    public static StringBuilder formatCoeff(final double coef, final StringBuilder s) {
        String coeffString = "";
        if (coef != 0) {
            coeffString = String.format("%.2f", Math.abs(coef));
            coeffString = coeffString.replace(",", ".");
            s.append(coef < 0 ? " - " : "")
             .append(Math.abs(coef) != 1 ? coeffString : "");
        }
        return s;
    }
}
