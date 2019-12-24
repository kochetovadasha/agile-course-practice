package ru.unn.agile.polygon.model;

import static java.lang.Math.abs;

public class Polygon {
    private static final int MINIMAL_NUMBER_OF_VERTICES = 3;
    private final Point[] vertices;
    private final Line[] sides;
    private final int size;
    private final double area;

    public Polygon(final Point... points) {
        if (points.length < MINIMAL_NUMBER_OF_VERTICES) {
            throw new IllegalArgumentException("A polygon must have at least three vertices");
        }

        this.vertices = points;
        this.size = points.length;
        this.sides = this.createSides();

        if (this.isSelfIntersecting()) {
            throw new IllegalArgumentException("Sides of polygon must not intersect");
        }

        this.area = this.calculateArea();
    }

    private double calculateArea() {
        double area = 0;

        for (int i = 0; i < this.size - 1; i++) {
            area += vertices[i].getX() * vertices[i + 1].getY();
            area -= vertices[i].getY() * vertices[i + 1].getX();
        }

        area += vertices[this.size - 1].getX() * vertices[0].getY();
        area -= vertices[this.size - 1].getY() * vertices[0].getX();
        area = area / 2;

        return area;
    }

    private Line[] createSides() {
        Line[] lines = new Line[this.size];

        for (int i = 0; i < this.size - 1; i++) {
            lines[i] = new Line(this.vertices[i], this.vertices[i + 1]);
        }
        lines[this.size - 1] = new Line(this.vertices[this.size - 1], this.vertices[0]);

        return lines;
    }

    private boolean isSelfIntersecting() {
        for (int i = 0; i < this.size; i++) {
            int prev = i - 1;
            int next = i + 1;

            for (int j = 0; j < prev; j++) {
                if (this.sides[i].intersectsLine(this.sides[j])) {
                    return true;
                }
            }

            for (int j = next; j < this.size; j++) {
                if (this.sides[i].intersectsLine(this.sides[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public double getArea() {
        return abs(this.area);
    }
}
