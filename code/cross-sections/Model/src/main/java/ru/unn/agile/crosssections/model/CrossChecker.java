package ru.unn.agile.crosssections.model;

public final class CrossChecker {

    private CrossChecker() { }

    public static boolean check(final Section section1, final Section section2) {
        return cross(section1.getA(), section1.getB(), section2.getA(), section2.getB());
    }

    private static int getArea(final Dot a, final Dot b, final Dot c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY())
                - (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    private static boolean innerCross(final int a, final int b, final int c, final int d) {
        return Math.max(Math.min(a, b), Math.min(c, d))
                <= Math.min(Math.max(a, b), Math.max(c, d));
    }

    private static boolean cross(final Dot a, final Dot b, final Dot c, final Dot d) {
        return innerCross(a.getX(), b.getX(), c.getX(), d.getX())
                && innerCross(a.getY(), b.getY(), c.getY(), d.getY())
                && getArea(a, b, c) * getArea(a, b, d) <= 0
                && getArea(c, d, a) * getArea(c, d, b) <= 0;
    }
}
