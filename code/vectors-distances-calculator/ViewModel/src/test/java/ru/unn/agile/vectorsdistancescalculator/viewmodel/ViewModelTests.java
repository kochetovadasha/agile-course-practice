package ru.unn.agile.vectorsdistancescalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.vectorsdistancescalculator.model.VectorsDistancesCalculator.Operation;

import static org.junit.Assert.*;

public class ViewModelTests {

    private static final double EPS = 0.01;

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
    public void canSetDefaultValues() {
        assertEquals("", viewModel.x1Property().get());
        assertEquals("", viewModel.y1Property().get());
        assertEquals("", viewModel.z1Property().get());
        assertEquals("", viewModel.x2Property().get());
        assertEquals("", viewModel.y2Property().get());
        assertEquals("", viewModel.z2Property().get());
        assertEquals(Operation.CALCULATE_L1_DISTANCE, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultFieldProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.x1Property().set("@");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.x1Property().set("2");
        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledByInit() {
        assertTrue(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setPositiveInputVectors();
        viewModel.x1Property().set("KABO");

        assertTrue(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledIfNotAllFieldsFilled() {
        viewModel.x1Property().setValue("1");
        assertTrue(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledIfDataFilledCorrect() {
        setMixedInputVectors();
        assertFalse(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void canSetCalculateLInfDistanceOperation() {
        viewModel.operationProperty().set(Operation.CALCULATE_LInf_DISTANCE);
        assertEquals(Operation.CALCULATE_LInf_DISTANCE, viewModel.operationProperty().get());
    }

    @Test
    public void calculateL1DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.calculate();
        double expectedL1 = 27.0;
        double actualL1 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL1, actualL1, EPS);
    }

    @Test
    public void calculateL2DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_L2_DISTANCE);
        viewModel.calculate();
        double expectedL2 = 15.84;
        double actualL2 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL2, actualL2, EPS);
    }

    @Test
    public void calculateL3DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_L3_DISTANCE);
        viewModel.calculate();
        double expectedL3 = 13.39;
        double actualL3 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL3, actualL3, EPS);
    }

    @Test
    public void calculateL4DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_L4_DISTANCE);
        viewModel.calculate();
        double expectedL4 = 12.39;
        double actualL4 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL4, actualL4, EPS);
    }

    @Test
    public void calculateLInfDistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_LInf_DISTANCE);
        viewModel.calculate();
        double expectedLInf = 11.0;
        double actualLInf = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedLInf, actualLInf, EPS);
    }

    @Test
    public void canSetReadyMessage() {
        setMixedInputVectors();
        String expected = Status.READY.toString();
        String actual = viewModel.statusFieldProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canSetSuccessMessage() {
        setMixedInputVectors();
        viewModel.calculate();
        String expected = Status.SUCCESS.toString();
        String actual = viewModel.statusFieldProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.x1Property().set("@");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusFieldProperty().get());
    }

    private void setPositiveInputVectors() {
        viewModel.x1Property().set("2");
        viewModel.y1Property().set("3");
        viewModel.z1Property().set("4");
        viewModel.x2Property().set("5");
        viewModel.y2Property().set("6");
        viewModel.z2Property().set("7");
    }

    private void setMixedInputVectors() {
        viewModel.x1Property().set("-2");
        viewModel.y1Property().set("3");
        viewModel.z1Property().set("-4");
        viewModel.x2Property().set("5");
        viewModel.y2Property().set("-6");
        viewModel.z2Property().set("7");
    }
}
