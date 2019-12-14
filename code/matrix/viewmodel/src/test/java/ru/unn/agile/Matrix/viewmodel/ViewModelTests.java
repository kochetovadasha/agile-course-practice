package ru.unn.agile.Matrix.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {

    private static final double DELTA = 1e-3;
    private ViewModel viewModel;
    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }
    @Test
    public void canSetDefaultValuesToFirstMatrix() {
        assertEquals("", viewModel.getFirstMatrixProperties()[0].get());
    }

    @Test
    public void canSetDefaultValuesToSecondMatrix() {
        assertEquals("", viewModel.getSecondMatrixProperties()[0].get());
    }

    @Test (expected = NumberFormatException.class)
    public void canNotTransposeMatrixIfNotFullFilled() {
        setWrongInputData();
        viewModel.transposeFirstMatrix();
    }

    @Test
    public void canTransposeFirstMatrixIfFilledWithCorrectValues() {
        setCorrectInputData();
        viewModel.transposeFirstMatrix();
        assertEquals(4.0, Double.parseDouble(viewModel.getFirstMatrixProperties()[1].get()), DELTA);
    }


    @Test
    public void canTransposeSecondMatrixIfFilledWithCorrectValues() {
        setCorrectInputData();
        viewModel.transposeSecondMatrix();
        assertEquals(4.0, Double.parseDouble(viewModel.getSecondMatrixProperties()[1].get()),
                DELTA);
    }

    @Test (expected = NumberFormatException.class)
    public void canNotTransposeMatrixWithNotNumericValues() {
        setWrongInputData();
        viewModel.transposeSecondMatrix();
    }

    @Test
    public void canCompareMatrices() {
        setCorrectInputData();
        viewModel.compareMatrices();
        assertTrue(Boolean.parseBoolean(viewModel.getResult()));
    }
    @Test (expected = NumberFormatException.class)
    public void canCompareOnlyCorrectFilledMatrices() {
        setWrongInputData();
        viewModel.compareMatrices();
    }
    @Test (expected = NumberFormatException.class)
    public void canNotSumMatricesIfNotFilledWithCorrectArgs() {
        setWrongInputData();
        viewModel.sumMatrices();
    }

    private void setCorrectInputData() {
        viewModel.setFirstMatrixProperties(new StringProperty[] {
                new SimpleStringProperty("1"),
                new SimpleStringProperty("2"),
                new SimpleStringProperty("3"),
                new SimpleStringProperty("4"),
                new SimpleStringProperty("5"),
                new SimpleStringProperty("6"),
                new SimpleStringProperty("7"),
                new SimpleStringProperty("8"),
                new SimpleStringProperty("9"),
        });
        viewModel.setSecondMatrixProperties(viewModel.getFirstMatrixProperties());
    }

    private void setWrongInputData() {
        viewModel.setFirstMatrixProperties(new StringProperty[] {
                new SimpleStringProperty("3"),
                new SimpleStringProperty("trash"),
                new SimpleStringProperty("1"),
                new SimpleStringProperty("f"),
                new SimpleStringProperty("fad"),
                new SimpleStringProperty(";;;"),
                new SimpleStringProperty("/r/n"),
                new SimpleStringProperty("1e"),
                new SimpleStringProperty("9="),
        });
        viewModel.setSecondMatrixProperties(viewModel.getFirstMatrixProperties());
    }

}
