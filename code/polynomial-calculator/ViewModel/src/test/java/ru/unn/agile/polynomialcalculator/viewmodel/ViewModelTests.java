package ru.unn.agile.polynomialcalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.polynomialcalculator.model.Polynomial;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isAddingValidInput() {
        setInputData("2.5", "1");
        viewModel.addPolynomial();
        assertEquals(new Polynomial(2.5, 1).getCoef(1),
                viewModel.getPolynomialsList().get(0).getCoef(1), 1e-10);
        assertEquals(new Polynomial(2.5, 1).getDegree(),
                viewModel.getPolynomialsList().get(0).getDegree());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNotAddedInvalidInput() {
        setInputData("-26vrt1.55", "2..645");
        viewModel.addPolynomial();
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNotAddedEmptyInput() {
        setInputData("", "");
        viewModel.addPolynomial();
    }

    @Test
    public void cantCalcPolynomialWhenPointListIsEmpty() {
        viewModel.calcPolynomialAdd();
        assertEquals(null, viewModel.getResult());
    }

    @Test
    public void canCalcPolyOfTwoPolynomials() {
        setInputData("1.1", "0");
        viewModel.addPolynomial();
        setInputData("2.4", "0");
        viewModel.addPolynomial();
        viewModel.calcPolynomialAdd();
        assertEquals(3.5, Double.parseDouble(viewModel.getResult()), 1e-12);
    }

    @Test
    public void isFormInputsEmptyAfterAddingNewPolynomials() {
        setInputData("-36.516", "3");
        viewModel.addPolynomial();
        assertTrue(viewModel.degreeProperty().get().isEmpty()
                && viewModel.coeffProperty().get().isEmpty()
        );
    }

    private void setInputData(final String coeff, final String degree) {
        viewModel.coeffProperty().set(coeff);
        viewModel.degreeProperty().set(degree);
    }
}
