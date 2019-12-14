package ru.unn.agile.Matrix.Model;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class MatrixTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private double delta = 0.001;

// # Matrix()
    @Test
    public void canCreateMatrix() {
        var matrix = new Matrix(3);
        assertNotNull(matrix);
    }

    @Test
    public void cantCreateMatrixWithZeroSize() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(
            Matrix.ExceptionMessage.ILLEGAL_ARG_EXC_EQUAL_ZERO.toString());

        var matrix = new Matrix(0);
    }

    @Test
    public void cantCreateMatrixWithNegativeSize() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(
            Matrix.ExceptionMessage.ILLEGAL_ARG_EXC_LESS_ZERO.toString());

        var matrix = new Matrix(-3);
    }

// # getSize()
    @Test
    public void canGetSizeFromMatrix() {
        int matrixSize = 3;
        var matrix = new Matrix(matrixSize);
        assertEquals(matrixSize, matrix.getSize());
    }

    @Test
    public void checkGetSizeWithNewInit() {
        int firstSize = 4;
        int secondSize = 12;

        var matrix = new Matrix(firstSize);
        assertEquals(firstSize, matrix.getSize());
        matrix = new Matrix(secondSize);
        assertEquals(secondSize, matrix.getSize());
    }

// # initByArray()
    @Test
    public void canInitMatrixByArray() {
        int matrixSize = 2;
        var matrix = new Matrix(matrixSize);
        double[] array = new double[] {2.5, 1.6, 4.3, 7.8};
        matrix.initByArray(array);
        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                double expected = array[i * matrixSize + j];
                double actual = matrix.getValue(i, j);
                assertEquals(expected, actual, delta);
            }
        }
    }

    @Test
    public void cantInitMatrixByArrayWithMoreValues() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(
            Matrix.ExceptionMessage.INDEX_OUT_BOUNDS_INPUT_ARRAY.toString());

        var matrix = new Matrix(2);
        double[] array = new double[] {2.5, 1.6, 4.3, 7.8, 3.6};
        matrix.initByArray(array);
    }

    @Test
    public void canInitMatrixByArrayWithLessValues() {
        int matrixSize = 2;
        var matrix = new Matrix(matrixSize);
        double[] array = new double[] {2.5, 1.6, 4.3};
        matrix.initByArray(array);

        assertEquals(array[0], matrix.getValue(0, 0), delta);
        assertEquals(array[1], matrix.getValue(0, 1), delta);
        assertEquals(array[2], matrix.getValue(1, 0), delta);
        assertEquals(0, matrix.getValue(1, 1), delta);
    }

// # getValue()
    @Test
    public void canGetValueFromMatrix() {
        var matrix = new Matrix(3);
        assertEquals(0, matrix.getValue(2, 2), delta);

        matrix.initByArray(new double[] {1, 1, 1, 0.5});
        assertEquals(0.5, matrix.getValue(1, 0), delta);
    }

    @Test
    public void cantGetValueFromMatrixOutOfBounds() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(
            Matrix.ExceptionMessage.INDEX_OUT_BOUNDS_MATRIX.toString());

        var matrix = new Matrix(2);

        matrix.initByArray(new double[] {1, 1, 1, 0.5});
        matrix.getValue(2, 0);
    }

// # equals()
    @Test
    public void checkEqualsWithOneObj() {
        var matrix = new Matrix(2);
        matrix.initByArray(new double[] {1, 1, 1, 0.5});

        assertTrue(matrix.equals(matrix));
    }

    @Test
    public void checkEqualsWithSameMatrix() {
        var matrixFirst = new Matrix(2);
        matrixFirst.initByArray(new double[] {0.31, 1, 1, 0.5});
        var matrixSecond = new Matrix(2);
        matrixSecond.initByArray(new double[] {0.31, 1, 1, 0.5});

        assertTrue(matrixFirst.equals(matrixSecond));
    }

    @Test
    public void checkEqualsWithNotSameMatrix() {
        var matrixFirst = new Matrix(2);
        matrixFirst.initByArray(new double[] {1, 1, 1, 0.5});
        var matrixSecond = new Matrix(2);
        matrixSecond.initByArray(new double[] {2, 1, 1, 0.5});

        assertFalse(matrixFirst.equals(matrixSecond));
    }

    @Test
    public void checkEqualsWithMatrixAndString() {
        var matrixFirst = new Matrix(2);
        matrixFirst.initByArray(new double[] {1, 1, 1, 0.5});

        assertFalse(matrixFirst.equals("Matrix"));
    }

    @Test
    public void checkEqualsMatrixsWithDifferentSizeInitByZero() {
        var matrixFirst = new Matrix(2);
        var matrixSecond = new Matrix(3);

        assertFalse(matrixFirst.equals(matrixSecond));
    }

    @Test
    public void checkEqualsMatrixsWithDifferentSize() {
        var matrixFirst = new Matrix(2);
        matrixFirst.initByArray(new double[] {0.31, 1, 1, 0.5});
        var matrixSecond = new Matrix(3);
        matrixSecond.initByArray(new double[] {0.31, 1, 1, 0.5, 1, 1});

        assertFalse(matrixFirst.equals(matrixSecond));
    }

    @Test
    public void checkEqualsWithDeltaError() {
        var matrixFirst = new Matrix(1);
        matrixFirst.initByArray(new double[] {1.8});
        var matrixSecond = new Matrix(1);
        matrixSecond.initByArray(new double[] {1.7999999999999998});

        assertTrue(matrixFirst.equals(matrixSecond));
    }

// # add()
    @Test
    public void canAddTwoMatrix() {
        var matrixFirst = new Matrix(2);
        matrixFirst.initByArray(new double[] {1, 1, 1, 0.5});
        var matrixSecond = new Matrix(2);
        matrixSecond.initByArray(new double[] {1.4, 2.8, -2.8, -0.5});
        var matrixAddResultExpected = new Matrix(2);
        matrixAddResultExpected.initByArray(new double[] {2.4, 3.8, -1.8, 0});

        var matrixAddResult = matrixFirst.add(matrixSecond);

        assertTrue(matrixAddResultExpected.equals(matrixAddResult));
    }

    @Test
    public void canAddMatrixAndZeroMatrix() {
        var matrixFirst = new Matrix(3);
        matrixFirst.initByArray(new double[] {1, 1, 1, 0.5, 69, -85.254, 87.2, 1.5, -98.4});
        var matrixSecond = new Matrix(3);
        matrixSecond.initByArray(new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0});

        var matrixAddResult = matrixFirst.add(matrixSecond);

        assertTrue(matrixFirst.equals(matrixAddResult));
    }

    @Test
    public void canAddMatrixsWithDifferentSizeFirst() {
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(
            Matrix.ExceptionMessage.INDEX_OUT_BOUNDS_MATRIX.toString());

        var matrixFirst = new Matrix(2);
        matrixFirst.initByArray(new double[] {1, 1, 1, 0.5});
        var matrixSecond = new Matrix(1);
        matrixSecond.initByArray(new double[] {12});

        matrixFirst.add(matrixSecond);

        thrown = ExpectedException.none();
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(
            Matrix.ExceptionMessage.INDEX_OUT_BOUNDS_MATRIX.toString());

        matrixSecond.add(matrixFirst);
    }

// # transpos()
    @Test
    public void canTransposMatrix() {
        var matrix = new Matrix(2);
        matrix.initByArray(new double[] {1, 1, -1, 0.5});
        var matrixAddResultExpected = new Matrix(2);
        matrixAddResultExpected.initByArray(new double[] {1, -1, 1, 0.5});

        var matrixTranspos = matrix.transpos();

        assertTrue(matrixAddResultExpected.equals(matrixTranspos));
    }

    @Test
    public void canTransposSimetricMatrix() {
        var matrix = new Matrix(3);
        matrix.initByArray(new double[] {1, 1, -1, 1, 0.58, 5.87, -1, 5.87, -8.58});

        var matrixTranspos = matrix.transpos();

        assertTrue(matrix.equals(matrixTranspos));
    }

    @Test
    public void canTransposMatrixTwice() {
        var matrix = new Matrix(3);
        double[] array = new double[] {1, 1, -1,
                                       0.5, 0.58, 5.87,
                                       -2.8, 8, -8.5};
        matrix.initByArray(array);

        var matrixTranspos = matrix.transpos();
        assertFalse(matrix.equals(matrixTranspos));

        matrixTranspos = matrixTranspos.transpos();
        assertTrue(matrix.equals(matrixTranspos));
    }
}
