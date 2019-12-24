package ru.unn.agile.Matrix.Model;

public class Matrix {

    public enum ExceptionMessage {
        ILLEGAL_ARG_EXC_EQUAL_ZERO("Trying to create a matrix with zero size"),
        ILLEGAL_ARG_EXC_LESS_ZERO("Trying to create a matrix with negative zero"),
        INDEX_OUT_BOUNDS_INPUT_ARRAY("Array size was more then matrix size"),
        INDEX_OUT_BOUNDS_MATRIX("Index out of bounds in matrix");

        private final String text;

        ExceptionMessage(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    private final int size;
    private double[] matrix;
    private final double delta = 0.001;

    public Matrix(final int size) {
        if (size == 0) {
            throw new IllegalArgumentException(
                ExceptionMessage.ILLEGAL_ARG_EXC_EQUAL_ZERO.toString());
        } else if (size < 0) {
            throw new IllegalArgumentException(
                ExceptionMessage.ILLEGAL_ARG_EXC_LESS_ZERO.toString());
        }

        matrix = new double[size * size];
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void initByArray(final double[] array) {
        if (array.length > size * size) {
            throw new IndexOutOfBoundsException(
                ExceptionMessage.INDEX_OUT_BOUNDS_INPUT_ARRAY.toString());
        }

        for (int i = 0; i < array.length; i++) {
            this.matrix[i] = array[i];
        }
    }

    public double getValue(final int i, final int j) {
        if (i * size + j >= size * size) {
            throw new IndexOutOfBoundsException(
                ExceptionMessage.INDEX_OUT_BOUNDS_MATRIX.toString());
        }

        return this.matrix[i * size + j];
    }

    @Override
    public int hashCode() {
        final int shift = 32;

        long temp = 0;
        int result = 0;

        for (int i = 0; i < size * size; i++) {
            temp = Double.doubleToLongBits(this.matrix[i]);
            result = (shift - i) * result + (int) (temp ^ (temp >>> shift));
        }

        return (int) (result * delta) + size;
    }

    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Matrix)) {
            return false;
        }
        return hashCode() == object.hashCode();
    }

    public Matrix add(final Matrix matrix) {
        if (this.size != matrix.getSize()) {
            throw new IndexOutOfBoundsException(
                ExceptionMessage.INDEX_OUT_BOUNDS_MATRIX.toString());
        }

        double[] array = new double[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i * size + j] = matrix.getValue(i, j) + this.matrix[i * size + j];
            }
        }

        var matrixResult = new Matrix(size);
        matrixResult.initByArray(array);
        return matrixResult;
    }

    public Matrix transpos() {
        double[] array = new double[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i * size + j] = this.matrix[j * size + i];
            }
        }

        var matrixResult = new Matrix(size);
        matrixResult.initByArray(array);
        return matrixResult;
    }
}
