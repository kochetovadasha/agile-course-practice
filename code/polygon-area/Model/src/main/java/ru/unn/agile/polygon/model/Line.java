package ru.unn.agile.polygon.model;

public class Line {
    private final Point point1;
    private final Point point2;
    private final double x;
    private final double y;

    public Line(final Point point1, final Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.x = point2.getX() - point1.getX();
        this.y = point2.getY() - point1.getY();
    }

    public boolean intersectsLine(final Line comparedLine) {
        double s, t;
        s = (-this.y * (this.getXPoint1() - comparedLine.getXPoint1())
                + this.x * (getYPoint1() - comparedLine.getYPoint1()))
                / (-comparedLine.getX() * this.y + this.x * comparedLine.getY());

        t = (comparedLine.getX() * (getYPoint1() - comparedLine.getYPoint1())
                - comparedLine.getY() * (getXPoint1() - comparedLine.getXPoint1()))
                / (-comparedLine.getX() * this.y + this.x * comparedLine.getY());

        return s > 0 && s < 1 && t > 0 && t < 1;
    }

    public Point getPoint1() {
        return this.point1;
    }

    public Point getPoint2() {
        return this.point2;
    }

    public double getXPoint1() {
        return this.point1.getX();
    }

    public double getXPoint2() {
        return this.point2.getX();
    }

    public double getYPoint1() {
        return this.point1.getY();
    }

    public double getYPoint2() {
        return this.point2.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
