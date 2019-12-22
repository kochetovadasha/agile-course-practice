package ru.unn.agile.vectoroperations.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.vectoroperations.model.Vector;
import ru.unn.agile.vectoroperations.model.Vector.Operation;

import static org.junit.Assert.*;

public class ViewModelTests {

    private static final double EPS = 0.01;

    private ViewModel viewModel;

    @Before
    public void beforeStart() {
        viewModel = new ViewModel();
    }

    @After
    public void afterFinish() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.x0Property().get());
        assertEquals("", viewModel.y0Property().get());
        assertEquals("", viewModel.z0Property().get());
        assertEquals("", viewModel.x0Property().get());
        assertEquals("", viewModel.y0Property().get());
        assertEquals("", viewModel.z0Property().get());
        assertEquals(Operation.CALCULATE_NORM, viewModel.opProperty().get());
        assertEquals("", viewModel.fieldResultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.fieldStatusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.x0Property().set("/");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.fieldStatusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.x1Property().set("6");
        assertEquals(Status.WAITING.toString(), viewModel.fieldStatusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledByInit() {
        assertTrue(viewModel.calculationDisablingFlagProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setPositiveInputVectors();
        viewModel.x0Property().set("WHAT");
        assertTrue(viewModel.calculationDisablingFlagProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledIfNotAllFieldsFilled() {
        viewModel.x1Property().setValue("0");
        assertTrue(viewModel.calculationDisablingFlagProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledIfDataFilledCorrect() {
        setMixedInputVectors();
        assertFalse(viewModel.calculationDisablingFlagProperty().get());
    }

    @Test
    public void canSetReadyMessage() {
        setMixedInputVectors();
        String expected = Status.READY.toString();
        String actual = viewModel.fieldStatusProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canSetSuccessMessage() {
        setMixedInputVectors();
        viewModel.calculate();
        String expected = Status.SUCCESS.toString();
        String actual = viewModel.fieldStatusProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.x0Property().set("/");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.fieldStatusProperty().get());
    }

    @Test
    public void canHideAdditionalFieldsByDefault() {
        assertFalse(viewModel.additionalVectorFieldDisablingFlagProperty().get());
    }


    @Test
    public void canShowAdditionalFields() {
        viewModel.opProperty().set(Operation.CALCULATE_SCALAR_MULT);
        assertTrue(viewModel.additionalVectorFieldDisablingFlagProperty().get());
    }

    @Test
    public void canHideAdditionalFields() {
        viewModel.opProperty().set(Operation.CALCULATE_SCALAR_MULT);
        viewModel.opProperty().set(Operation.CALCULATE_NORMALIZED_VECTOR);
        assertFalse(viewModel.additionalVectorFieldDisablingFlagProperty().get());
    }

    @Test
    public void canEnableCalculateButtonWhenOnlyOneVector() {
        setPositiveInputVector();
        viewModel.opProperty().set(Operation.CALCULATE_NORMALIZED_VECTOR);
        assertFalse(viewModel.calculationDisablingFlagProperty().get());
    }

    @Test
    public void canCalculateVectorNorm() {
        setPositiveInputVector();
        viewModel.opProperty().set(Operation.CALCULATE_NORM);
        viewModel.calculate();
        double expected = 3.7416;
        double actual = Double.parseDouble(viewModel.fieldResultProperty().get());
        assertEquals(expected, actual, EPS);
    }

    @Test
    public void canCalculateNormalizedVector() {
        setPositiveInputVector();
        viewModel.opProperty().set(Operation.CALCULATE_NORMALIZED_VECTOR);
        viewModel.calculate();
        Vector expectedVector = new Vector(0.2672612419124244,
                                           0.5345224838248488,
                                           0.8017837257372732);
        String expected = expectedVector.toString();
        String actual = viewModel.fieldResultProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canCalculateVectorsScalarMult() {
        setMixedInputVectors();
        viewModel.opProperty().set(Operation.CALCULATE_SCALAR_MULT);
        viewModel.calculate();
        String expected = "-32.0";
        String actual = viewModel.fieldResultProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canCalculateVectorsVectorMult() {
        setPositiveInputVectors();
        viewModel.opProperty().set(Operation.CALCULATE_VECTOR_MULT);
        viewModel.calculate();
        Vector expectedVector = new Vector(-3.0, -6.0, -3.0);
        String expected = expectedVector.toString();
        String actual = viewModel.fieldResultProperty().get();
        assertEquals(expected, actual);
    }

    private void setPositiveInputVector() {
        viewModel.x0Property().set("1");
        viewModel.y0Property().set("2");
        viewModel.z0Property().set("3");
    }

    private void setPositiveInputVectors() {
        viewModel.x0Property().set("1");
        viewModel.y0Property().set("2");
        viewModel.z0Property().set("3");
        viewModel.x1Property().set("4");
        viewModel.y1Property().set("5");
        viewModel.z1Property().set("6");
    }

    private void setMixedInputVectors() {
        viewModel.x0Property().set("-1");
        viewModel.y0Property().set("2");
        viewModel.z0Property().set("-3");
        viewModel.x1Property().set("4");
        viewModel.y1Property().set("-5");
        viewModel.z1Property().set("6");
    }
}
