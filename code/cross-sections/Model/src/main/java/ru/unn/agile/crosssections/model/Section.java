package ru.unn.agile.crosssections.model;

public class Section {

    private Dot a;
    private Dot b;

    public Section(final Dot a, final Dot b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Points should not be in one place");
        }
        this.a = a;
        this.b = b;
    }

    public Dot getA() {
        return a;
    }

    public Dot getB() {
        return b;
    }
}
