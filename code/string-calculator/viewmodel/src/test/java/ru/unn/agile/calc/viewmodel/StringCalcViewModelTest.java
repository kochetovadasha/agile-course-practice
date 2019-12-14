package ru.unn.agile.calc.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalcViewModelTest {

    private static final String ERROR_MESSAGE = "Error. Check your input";

    private StringCalcViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new StringCalcViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canCalculateExpression1() {
        setExpression("1+1");
        calculate();
        assertEquals("2.0", getResult());
    }

    @Test
    public void canCalculateExpression2() {
        setExpression("1+2*3-4/5");
        calculate();
        assertEquals("6.2", getResult());
    }

    @Test
    public void canCalculateValidExpressionWithSpaces() {
        setExpression(" 1 + 1 * 3 - 5 / 10");
        calculate();
        assertEquals("3.5", getResult());
    }

    @Test
    public void canCalculateSingleDigitExpression() {
        setExpression("111");
        calculate();
        assertEquals("111.0", getResult());
    }

    @Test
    public void isResultTheSameForSameExpression() {
        setExpression("1+1");

        calculate();
        String r1 = getResult();

        calculate();
        String r2 = getResult();

        assertEquals(r1, r2);
    }

    @Test
    public void canNotCalculateInvalidExpression1() {
        setExpression("+-*/");
        calculate();
        assertEquals(ERROR_MESSAGE, getResult());
    }

    @Test
    public void isCalculateButtonDisabledForSpaceSymbol() {
        setExpression(" ");
        assertTrue(isCalculateButtonDisabled());
    }

    @Test
    public void isCalculateButtonDisabledForLetterString() {
        setExpression("ABC");
        assertTrue(isCalculateButtonDisabled());
    }

    @Test
    public void areSpacesRemoving() {
        setExpression("1 + 2 + 3");
        assertEquals("1+2+3", getExpression());
    }

    private boolean isCalculateButtonDisabled() {
        return viewModel.calculationDisabledProperty().get();
    }

    private void setExpression(final String expression) {
        viewModel.expressionTfProperty().setValue(expression);
    }

    private String getExpression() {
        return viewModel.expressionTfProperty().getValue();
    }

    private void calculate() {
        viewModel.calculate();
    }

    private String getResult() {
        return viewModel.resultLblProperty().get();
    }

}
