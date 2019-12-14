package ru.unn.agile.Matrix.viewmodel;
import ru.unn.agile.Matrix.Model.Matrix;
import javafx.beans.property.*;

public class ViewModel {

    private static final int DEFAULT_MATRIX_SIZE = 3;
    private Matrix firstMatrix = new Matrix(DEFAULT_MATRIX_SIZE);
    private Matrix secondMatrix = new Matrix(DEFAULT_MATRIX_SIZE);
    private StringProperty[] firstMatrixProperties =
            new SimpleStringProperty[DEFAULT_MATRIX_SIZE * DEFAULT_MATRIX_SIZE];
    private StringProperty[] secondMatrixProperties =
            new SimpleStringProperty[DEFAULT_MATRIX_SIZE * DEFAULT_MATRIX_SIZE];
    private StringProperty[] resultMatrixProperties =
            new SimpleStringProperty[DEFAULT_MATRIX_SIZE * DEFAULT_MATRIX_SIZE];
    private final StringProperty result = new SimpleStringProperty();

    public ViewModel() {
        for (int i = 0; i < firstMatrixProperties.length; i++) {
            firstMatrixProperties[i] = new SimpleStringProperty("");
        }
        for (int i = 0; i < secondMatrixProperties.length; i++) {
            secondMatrixProperties[i] = new SimpleStringProperty("");
        }
        for (int i = 0; i < resultMatrixProperties.length; i++) {
            resultMatrixProperties[i] = new SimpleStringProperty("");
        }
        result.set("");
    }

    public boolean canTranspose() {
        boolean result = true;
        StringProperty[] properties = getFirstMatrixProperties();
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].get().isEmpty()) {
                result = false;
            }
        }
        return result;
    }

    public StringProperty[] getFirstMatrixProperties() {
        return firstMatrixProperties;
    }

    public StringProperty getFirstMatrixPropertiesByIndex(final String index) {
        return firstMatrixProperties[Integer.parseInt(index)];
    }

    public StringProperty getSecondMatrixPropertiesByIndex(final String index) {
        return secondMatrixProperties[Integer.parseInt(index)];
    }

    public StringProperty getResultMatrixPropertiesByIndex(final String index) {
        return resultMatrixProperties[Integer.parseInt(index)];
    }

    public void setFirstMatrixProperties(final StringProperty[] firstMatrixProperties) {
        this.firstMatrixProperties = firstMatrixProperties;
    }

    public StringProperty[] getSecondMatrixProperties() {
        return secondMatrixProperties;
    }

    public void setSecondMatrixProperties(final StringProperty[] secondMatrixProperties) {
        this.secondMatrixProperties = secondMatrixProperties;
    }

    private boolean isPossibleToProcess(final int matrixNumber) {
        boolean result = true;
        StringProperty[] matrixProperties;
        switch (matrixNumber) {
            case 1:
                matrixProperties = firstMatrixProperties;
                break;
            case 2:
                matrixProperties = secondMatrixProperties;
                break;
            default:
                return false;
        }
        for (int i = 0; i < matrixProperties.length; i++) {
            if (!matrixProperties[i].get().isEmpty()) {
                try {
                    Double.parseDouble(matrixProperties[i].get());
                } catch (NumberFormatException e) {
                    result = false;
                }
            } else {
                result = false;
            }

        }
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public void compareMatrices() {
        if (!isPossibleToProcess(1) || !isPossibleToProcess(2)) {
            throw new NumberFormatException("Matrices full filled with wrong args");
        }
        firstMatrix.initByArray(getFirstMatrixValues());
        secondMatrix.initByArray(getSecondMatrixValues());
        result.set(Boolean.toString(firstMatrix.equals(secondMatrix)));
        System.out.println(result.get());
    }

    public void transposeFirstMatrix() {
        if (!isPossibleToProcess(1)) {
            throw new NumberFormatException("Matrix must be filled");
        }
        firstMatrix.initByArray(getFirstMatrixValues());
        double[] transposedValues = convertMatrixToArray(firstMatrix.transpos());
        int i;
        for (i = 0; i < firstMatrixProperties.length; i++) {
            firstMatrixProperties[i].set(String.valueOf(transposedValues[i]));
        }
    }

    public void transposeSecondMatrix() {
        if (!isPossibleToProcess(2)) {
            throw new NumberFormatException("Matrix must be filled with correct numeric arguments");
        }
        secondMatrix.initByArray(getSecondMatrixValues());
        double[] transposedValues = convertMatrixToArray(secondMatrix.transpos());
        int i;
        for (i = 0; i < secondMatrixProperties.length; i++) {
            secondMatrixProperties[i].set(String.valueOf(transposedValues[i]));
        }
    }

    public void sumMatrices() {
        if (!isPossibleToProcess(1) || !isPossibleToProcess(2)) {
            throw new NumberFormatException("Matrices must be filled with correct numeric args");
        }
        firstMatrix.initByArray(getFirstMatrixValues());
        secondMatrix.initByArray(getSecondMatrixValues());
        Matrix resultMatrix = firstMatrix.add(secondMatrix);
        double[] sumValues = convertMatrixToArray(resultMatrix);
        for (int i = 0; i < resultMatrixProperties.length; i++) {
            resultMatrixProperties[i].set(String.valueOf(sumValues[i]));
        }
    }

    private double[] convertMatrixToArray(final Matrix matrix) {
        double[] result = new double[matrix.getSize() * matrix.getSize()];
        for (int i = 0; i < matrix.getSize(); i++) {
            int j;
            for (j = 0; j < matrix.getSize(); j++) {
                result[i * matrix.getSize() + j] = matrix.getValue(i, j);
            }
        }
        return result;
    }

    private double[] getFirstMatrixValues() {
        double[] result = new double[firstMatrixProperties.length];
        for (int i = 0; i < firstMatrixProperties.length; i++) {
            result[i] = Double.parseDouble(firstMatrixProperties[i].get());
        }
        return result;
    }

    private double[] getSecondMatrixValues() {
        double[] result = new double[secondMatrixProperties.length];
        for (int i = 0; i < secondMatrixProperties.length; i++) {
            result[i] = Double.parseDouble(secondMatrixProperties[i].get());
        }
        return result;
    }
}
