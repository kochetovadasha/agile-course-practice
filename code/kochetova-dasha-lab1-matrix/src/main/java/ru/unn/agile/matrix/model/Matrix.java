package ru.unn.agile.Matrix.model;

public class Matrix {
    private final int size;
    private double[] matrix;

    public Matrix(final int size) {
        if (size == 0) {
            throw new IllegalArgumentException("Trying to create a matrix with zero size");
        } else if (size < 0) {
            throw new IllegalArgumentException("Trying to create a matrix with negative zero");
        }

        matrix = new double[size];
        this.size = size;
    }

    public int getSize() {
        return size;
    }

/*    public void initByArray(double[] array) {
        for (int i = 0; i < array.length; i++) {
            this.matrix[i] = array[i];
        }
    }

    public double getValue(int i, int j) {
        return this.matrix[i * size + j];
    }*/
}