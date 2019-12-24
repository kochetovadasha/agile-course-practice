package ru.unn.agile.polygon.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {
    private double delta = 0.0001;
    private final Point p1 = new Point(0, 0);
    private final Point p2 = new Point(1, 0);
    private final Point p3 = new Point(1, 1);
    private final Point p4 = new Point(5, 1);

    private final Line line1 = new Line(p1, p2);
    private final Line line2 = new Line(p2, p3);
    private final Line line3 = new Line(p3, p4);
    private final Line line4 = new Line(p1, p4);

    @Test(expected = Test.None.class)
    public void canCreateLine() {
        Line line = new Line(p1, p2);
    }

    @Test
    public void canGetLinePoint1() {
        assertEquals(p1, line1.getPoint1());
    }

    @Test
    public void canGetLinePoint2() {
        assertEquals(p2, line1.getPoint2());
    }


    @Test
    public void canGetXCoordinateFromPoint1() {
        assertEquals(p1.getX(), line1.getXPoint1(), delta);
    }

    @Test
    public void canGetYCoordinateFromPoint1() {
        assertEquals(p1.getY(), line1.getYPoint1(), delta);
    }

    @Test
    public void canGetXCoordinateFromPoint2() {
        assertEquals(p2.getX(), line1.getXPoint2(), delta);
    }

    @Test
    public void canGetYCoordinateFromPoint2() {
        assertEquals(p2.getY(), line1.getYPoint2(), delta);
    }

    @Test
    public void canGetXLineCoordinate() {
        assertEquals(p2.getX() - p1.getX(), line1.getX(), delta);
    }

    @Test
    public void canGetYLineCoordinate() {
        assertEquals(p2.getY() - p1.getY(), line1.getY(), delta);
    }

    @Test
    public void areIntersectedLinesInterceptingEachOther() {
        assertTrue(line2.intersectsLine(line4));
    }

    @Test
    public void areNotIntersectedLinesInterceptingEachOther() {
        assertFalse(line1.intersectsLine(line3));
    }

    @Test
    public void areLinesWithCommonBeginPointInterceptingEachOther() {
        assertFalse(line1.intersectsLine(line2));
    }
}
