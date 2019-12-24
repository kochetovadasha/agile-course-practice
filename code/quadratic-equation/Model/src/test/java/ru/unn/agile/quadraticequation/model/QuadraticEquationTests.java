package ru.unn.agile.quadraticequation.model;

import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.*;

public class QuadraticEquationTests {

    private final double delta = 0.01;

    @Test
    public void canCreateWithPositiveIndexes() {
        QuadraticEquation qe = new QuadraticEquation(1, 2, 3);
        assertNotNull(qe);
    }

    @Test
    public void canCreateWithNegativeIndexes() {
        QuadraticEquation qe = new QuadraticEquation(-1, -2, -3);
        assertNotNull(qe);
    }

    @Test
    public void canCreateWithZeroIndexes() {
        QuadraticEquation qe = new QuadraticEquation(1, 0, 0);
        assertNotNull(qe);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateWithAllZeroIndexes() {
        QuadraticEquation qe = new QuadraticEquation(0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateWithAAndBZeroIndexes() {
        QuadraticEquation qe = new QuadraticEquation(0, 0, 10);
    }

    @Test public void canSolveSimpleEquation() {
        QuadraticEquation qe = new QuadraticEquation(2, 2, -4);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(1, 0);
        solution[1] = new ComplexNumber(-2, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
        assertEquals(solution[1], actual[1]);
    }

    @Test public void canSolveEquationWithZeroACoeff() {
        QuadraticEquation qe = new QuadraticEquation(0, 3, -9);
        ComplexNumber[] solution = new ComplexNumber[1];
        solution[0] = new ComplexNumber(3, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
    }

    @Test public void solutionWithZeroACoeffGivesOneX() {
        QuadraticEquation qe = new QuadraticEquation(0, 4, -16);
        ComplexNumber[] solution = new ComplexNumber[1];
        solution[0] = new ComplexNumber(4, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(1, solution.length);
    }

    @Test public void canSolveEquationWithZeroBCoeff() {
        QuadraticEquation qe = new QuadraticEquation(1, 0, -49);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(7, 0);
        solution[1] = new ComplexNumber(-7, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
        assertEquals(solution[1], actual[1]);
    }

    @Test public void canSolveEquationWithZeroCCoeff() {
        QuadraticEquation qe = new QuadraticEquation(4, 12, 0);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(0, 0);
        solution[1] = new ComplexNumber(-3, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
        assertEquals(solution[1], actual[1]);
    }

    @Test public void canSolveEquationWithComplexSolution() {
        QuadraticEquation qe = new QuadraticEquation(1, 1, 4);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(-0.5, 1.93);
        solution[1] = new ComplexNumber(-0.5, -1.93);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0].getReal(), actual[0].getReal(), delta);
        assertEquals(solution[0].getImaginary(), actual[0].getImaginary(), delta);
        assertEquals(solution[1].getReal(), actual[1].getReal(), delta);
        assertEquals(solution[1].getImaginary(), actual[1].getImaginary(), delta);
    }

    @Test public void canConvertToString() {
        QuadraticEquation qe = new QuadraticEquation(1, 1, 4);
        assertEquals("(x^2) + x + 4.00", qe.toString());
    }

    @Test
    public void canGiveHashCode() {
        QuadraticEquation qe = new QuadraticEquation(1, 1, 1);
        assertTrue((qe.hashCode() >= 0));
    }

    @Test
    public void notEqualEquationAreNotEqual() {
        QuadraticEquation qe1 = new QuadraticEquation(1, 1, 1);
        QuadraticEquation qe2 = new QuadraticEquation(2, 2, 2);
        assertNotEquals(qe1, qe2);
    }

    @Test
    public void equalEquationAreEqual() {
        QuadraticEquation qe1 = new QuadraticEquation(1, 1, 1);
        QuadraticEquation qe2 = new QuadraticEquation(1, 1, 1);
        assertEquals(qe1, qe2);
    }

    @Test
    public void equationsWithDifferentACoeffsNotEqual() {
        QuadraticEquation qe1 = new QuadraticEquation(2, 1, 1);
        QuadraticEquation qe2 = new QuadraticEquation(1, 1, 1);
        assertNotEquals(qe1, qe2);
    }

    @Test
    public void equationsWithDifferentBCoeffsNotEqual() {
        QuadraticEquation qe1 = new QuadraticEquation(1, 2, 1);
        QuadraticEquation qe2 = new QuadraticEquation(1, 1, 1);
        assertNotEquals(qe1, qe2);
    }

    @Test
    public void equationsWithDifferentCCoeffsNotEqual() {
        QuadraticEquation qe1 = new QuadraticEquation(1, 1, 2);
        QuadraticEquation qe2 = new QuadraticEquation(1, 1, 1);
        assertNotEquals(qe1, qe2);
    }
}
