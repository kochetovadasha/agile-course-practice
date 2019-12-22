package ru.unn.agile.vectors;

import org.junit.Test;
import ru.unn.agile.vectors.model.Vector;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void canCreateVectorWithPositiveCoordinates() {
        Vector vect = new Vector(1, 2, 3);
        assertNotNull(vect);
    }

    @Test
    public void canCreateVectorWithNegativeCoordinates() {
        Vector vect = new Vector(-1, -2, -3);
        assertNotNull(vect);
    }

    @Test
    public void canCreateTwoEqualVectorsWithPositiveCoordinates() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 2, 3);
        boolean tmp = Vector.isEqual(v2, v1);
        assertTrue(tmp);
    }

    @Test
    public void canCreateTwoEqualVectorsWithNegativeCoordinates() {
        Vector v1 = new Vector(-1, -2, -3);
        Vector v2 = new Vector(-1, -2, -3);
        boolean tmp = Vector.isEqual(v2, v1);
        assertTrue(tmp);
    }

    @Test
    public void canFindTheLengthNormWithPositiveCoordinates() {
        Vector vect = new Vector(1, 2, 3);
        double tmp = 3.7416573867739413;
        double len = Vector.getLengthNorm(vect);
        assertEquals(len, tmp);
    }

    @Test
    public void canFindTheLengthNormWithNegativeCoordinates() {
        Vector vect = new Vector(-1, -2, -3);
        double tmp = 3.7416573867739413;
        double len = Vector.getLengthNorm(vect);
        assertEquals(len, tmp);
    }

    @Test
    public void canCheckIsOrtogonal() {
        Vector v1 = new Vector(0, 1, 0);
        Vector v2 = new Vector(0, 0, 1);
        boolean tmp = Vector.isOrthogonal(v2, v1);
        assertTrue(tmp);
    }

    @Test
    public void canAddTwoVectorsWithPositiveCoordinates() {
        Vector v1 = new Vector(1, 1, 1);
        Vector v2 = new Vector(2, 2, 2);
        Vector actRes = new Vector(3, 3, 3);
        Vector expRes = Vector.getSum(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canAddTwoVectorsWithNegativeCoordinates() {
        Vector v1 = new Vector(-1, -1, -1);
        Vector v2 = new Vector(-2, -2, -2);
        Vector actRes = new Vector(-3, -3, -3);
        Vector expRes = Vector.getSum(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canAddTwoVectorsWithDifferentSignCoordinates() {
        Vector v1 = new Vector(-1, 1, -1);
        Vector v2 = new Vector(2, -2, 2);
        Vector actRes = new Vector(1, -1, 1);
        Vector expRes = Vector.getSum(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDifferenceTwoVectorsWithPositiveCoordinates() {
        Vector v1 = new Vector(1, 1, 1);
        Vector v2 = new Vector(2, 2, 2);
        Vector actRes = new Vector(1, 1, 1);
        Vector expRes = Vector.getDiff(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDifferenceTwoVectorsWithNegativeCoordinates() {
        Vector v1 = new Vector(-1, -1, -1);
        Vector v2 = new Vector(-2, -2, -2);
        Vector actRes = new Vector(-1, -1, -1);
        Vector expRes = Vector.getDiff(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDifferenceTwoVectorsWithDifferentSignCoordinates() {
        Vector v1 = new Vector(-1, 1, -1);
        Vector v2 = new Vector(2, -2, 2);
        Vector actRes = new Vector(3, -3, 3);
        Vector expRes = Vector.getDiff(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDoScalarMulterenceTwoVectorsWithPositiveCoordinates() {
        Vector v1 = new Vector(1, 1, 1);
        Vector v2 = new Vector(2, 2, 2);
        double actRes = 6;
        double expRes = Vector.getScalarMult(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test()
    public void canDoScalarMultTwoVectorsWithNegativeCoordinates() {
        Vector v1 = new Vector(-1, -1, -1);
        Vector v2 = new Vector(-2, -2, -2);
        double actRes = 6;
        double expRes = Vector.getScalarMult(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDoScalarMultTwoVectorsWithDifferentSignCoordinates() {
        Vector v1 = new Vector(-1, 1, 1);
        Vector v2 = new Vector(2, -2, 2);
        double actRes = -2;
        double expRes = Vector.getScalarMult(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDoVectMulterenceTwoVectorsWithPositiveCoordinates() {
        Vector v1 = new Vector(1, 1, 1);
        Vector v2 = new Vector(2, 2, 2);
        Vector actRes = new Vector(0, 0, 0);
        Vector expRes = Vector.getVectMult(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDoVectMultTwoVectorsWithNegativeCoordinates() {
        Vector v1 = new Vector(-1, -1, -1);
        Vector v2 = new Vector(-2, -2, -2);
        Vector actRes = new Vector(0, 0, 0);
        Vector expRes = Vector.getVectMult(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canDoVectMultTwoVectorsWithDifferentSignCoordinates() {
        Vector v1 = new Vector(-1, 1, 1);
        Vector v2 = new Vector(2, -2, 2);
        Vector actRes = new Vector(-4, 4, 0);
        Vector expRes = Vector.getVectMult(v2, v1);
        assertEquals(expRes, actRes);
    }

    @Test
    public void canCheckIsComplonarWithComplonarVectors() {
        Vector v1 = new Vector(1, 1, 1);
        Vector v2 = new Vector(2, 2, 2);
        boolean tmp = Vector.isComplanar(v2, v1);
        assertTrue(tmp);
    }

    @Test
    public void canCheckIsComplonarWithNotComplonarVectors() {
        Vector v1 = new Vector(-1, 1, 1);
        Vector v2 = new Vector(2, -2, 2);
        boolean tmp = Vector.isComplanar(v2, v1);
        assertFalse(tmp);
    }

    @Test
    public void canGetX() {
        Vector v1 = new Vector(-1, 1, 1);
        double actualRes = v1.getX();
        double expectedRes = -1.0;
        assertEquals(actualRes, expectedRes);
    }

    @Test
    public void canGetY() {
        Vector v1 = new Vector(-1, 1, 1);
        double actualRes = v1.getY();
        double expectedRes = 1.0;
        assertEquals(actualRes, expectedRes);
    }

    @Test
    public void canGetZ() {
        Vector v1 = new Vector(-1, 1, 1);
        double actualRes = v1.getZ();
        double expectedRes = 1.0;
        assertEquals(actualRes, expectedRes);
    }
}
