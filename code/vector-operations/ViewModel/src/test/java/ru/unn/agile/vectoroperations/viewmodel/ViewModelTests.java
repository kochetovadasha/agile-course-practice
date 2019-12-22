package ru.unn.agile.vectoroperations.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        viewModel.x1Property().set("/");
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
        viewModel.x1Property().set("WHAT");

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
        viewModel.x1Property().set("/");
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
