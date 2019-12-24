package ru.unn.agile.polygon.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PointTest {

    private final double delta = 0.001;

    private final Point pointA = new Point();
    private final Point pointB = new Point(2, 2.5);
    private final Point pointC = new Point(pointA);

    @Test(expected = Test.None.class)
    public void canCreatePoint() {
        Point p1 = new Point();
        Point p2 = new Point(3,  2.5);
        Point p3 = new Point(-10, -0.11);
        Point p4 = new Point(p1);
    }

    @Test
    public void canGetXCoordinate() {
        assertEquals(2, pointB.getX(), delta);
    }

    @Test
    public void canGetYCoordinate() {
        assertEquals(2.5, pointB.getY(), delta);
    }

    @Test
    public void areXCoordinatesOfEqualPointsEqual() {
        assertEquals(pointA.getX(), pointC.getX(), delta);
    }

    @Test
    public void areYCoordinatesOfEqualPointsEqual() {
        assertEquals(pointA.getY(), pointC.getY(), delta);
    }

    @Test
    public void canGetDistanceToAnotherPoint() {
        assertEquals(pointA.distanceTo(pointB), 3.2015, delta);
    }
}
