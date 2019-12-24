package ru.unn.agile.polygon.model;

public class Point {
    private final double x;
    private final double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(final Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public double distanceTo(final Point pointB) {
        return Math.sqrt(Math.pow(this.x - pointB.x, 2) + Math.pow(this.y - pointB.y, 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
