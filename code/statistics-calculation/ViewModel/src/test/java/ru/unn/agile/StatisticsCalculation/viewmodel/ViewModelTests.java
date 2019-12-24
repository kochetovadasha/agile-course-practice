package ru.unn.agile.statisticscalculation.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void canSetDefaultValuesLabels() {
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(OperationStatus.WAITING_DATA.toString(), viewModel.getOperationStatus());
        assertEquals(DataStatus.WAITING.toString(), viewModel.getDataStatus());
        assertEquals(InputDataStatus.WAITING.toString(), viewModel.getInputDataStatus());
    }
    @Test
    public void canSetDefaultValuesTextFields() {
        assertEquals("", viewModel.newProbabilityProperty().get());
        assertEquals("", viewModel.newValueProperty().get());
        assertEquals("", viewModel.operationParameterProperty().get());
    }
    @Test
    public void canSetDefaultButtons() {
        assertTrue(viewModel.calculationDisabledProperty().get());
        assertTrue(viewModel.deleteDisabledProperty().get());
    }
    @Test
    public void canSetParametersPartInvisibleByDefault() {
        assertFalse(viewModel.isEnterParameterVisible());
    }
    @Test
    public void canAddCorrectTableElement() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("0.2");

        viewModel.updateTableElement();

        assertEquals("1", viewModel.getListData().get(0).getValue());
        assertEquals("0.2", viewModel.getListData().get(0).getProbability());
    }
    @Test
    public void canAddCorrectTableElementAndCheckStatusWaiting() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("0.2");

        viewModel.updateTableElement();

        assertEquals(InputDataStatus.WAITING.toString(), viewModel.inputDataStatusProperty().get());
    }
    @Test
    public void canNotAddTableElementWithProbabilityMoreThen1() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.1");

        viewModel.updateTableElement();

        assertTrue(viewModel.getListData().isEmpty());
        assertEquals(InputDataStatus.BAD_FORMAT.toString(),
                viewModel.inputDataStatusProperty().get());
    }
    @Test
    public void canNotAddTableElementWithProbabilityMoreThen1AndStatusBad() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.1");

        viewModel.updateTableElement();

        assertEquals(InputDataStatus.BAD_FORMAT.toString(),
                viewModel.inputDataStatusProperty().get());
    }
    @Test
    public void canPushUpdateButtonWithCorrectInput() {
        viewModel.newValueProperty().set("2");
        viewModel.newProbabilityProperty().set("0.5");

        assertFalse(viewModel.isUpdateDisabled());
    }
    @Test
    public void canPushUpdateButtonWithDoubleInputInValue() {
        viewModel.newValueProperty().set("2.1");
        viewModel.newProbabilityProperty().set("0.2");

        assertFalse(viewModel.isUpdateDisabled());
    }
    @Test
    public void canNotPushUpdateButtonWithIncorrectInput() {
        viewModel.newValueProperty().set("2");
        viewModel.newProbabilityProperty().set("1.5");

        assertTrue(viewModel.isUpdateDisabled());
    }
    @Test
    public void canPushDeleteButtonWithNotEmptyInput() {
        viewModel.newValueProperty().set("2");
        viewModel.newProbabilityProperty().set("0.5");

        assertFalse(viewModel.isDeleteDisabled());
    }
    @Test
    public void canSetInputDataWaitingWithEmptyProperty() {
        viewModel.newValueProperty().set("2");
        viewModel.newProbabilityProperty().set("");

        assertEquals(InputDataStatus.WAITING.toString(), viewModel.getInputDataStatus());
    }
    @Test
    public void canSetInputDataBadFormatWithErrorProbability() {
        viewModel.newValueProperty().set("2");
        viewModel.newProbabilityProperty().set("10");

        assertEquals(InputDataStatus.BAD_FORMAT.toString(), viewModel.getInputDataStatus());
    }
    @Test
    public void canSetInputDataBadFormatWithString() {
        viewModel.newValueProperty().set("hello");
        viewModel.newProbabilityProperty().set("!");

        assertEquals(InputDataStatus.BAD_FORMAT.toString(), viewModel.getInputDataStatus());
    }
    @Test
    public void canSetInputDataBadFormatWithOneError() {
        viewModel.newValueProperty().set("hello");
        viewModel.newProbabilityProperty().set("0.1");

        assertEquals(InputDataStatus.BAD_FORMAT.toString(), viewModel.getInputDataStatus());
    }
    @Test
    public void canSetInputDataReadyWithDoubleInValue() {
        viewModel.newValueProperty().set("1.1");
        viewModel.newProbabilityProperty().set("0.1");

        assertEquals(InputDataStatus.READY.toString(), viewModel.getInputDataStatus());
    }
    @Test
    public void canSetInputDataReady() {
        viewModel.newValueProperty().set("2");
        viewModel.newProbabilityProperty().set("0.4");

        assertEquals(InputDataStatus.READY.toString(), viewModel.getInputDataStatus());
    }
    @Test
    public void canUpdateSelectedElement() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.1");
        viewModel.updateTableElement();

        viewModel.setSelectedElement(0);
        viewModel.newValueProperty().set("20");
        viewModel.newProbabilityProperty().set("0.2");
        viewModel.updateTableElement();

        assertEquals("20", viewModel.getListData().get(0).getValue());
        assertEquals("0.2", viewModel.getListData().get(0).getProbability());
    }
    @Test
    public void canDeleteInputData() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.1");

        viewModel.deleteTableElement(-1);

        assertTrue(viewModel.getListData().isEmpty());
        assertEquals("", viewModel.newValueProperty().get());
        assertEquals("", viewModel.newProbabilityProperty().get());
    }
    @Test
    public void canDeleteSelectedElement() {
        viewModel.newValueProperty().set("100");
        viewModel.newProbabilityProperty().set("0.1");
        viewModel.updateTableElement();
        viewModel.newValueProperty().set("200");
        viewModel.newProbabilityProperty().set("0.2");
        viewModel.updateTableElement();

        viewModel.deleteTableElement(1);

        assertEquals(1, viewModel.getListData().size());
        assertEquals("100", viewModel.getListData().get(0).getValue());
        assertEquals("0.1", viewModel.getListData().get(0).getProbability());
    }
    @Test
    public void canHandleDeleteWithInvalidIndex() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.4");
        viewModel.updateTableElement();

        viewModel.deleteTableElement(2);

        assertEquals(1, viewModel.getListData().size());
        assertEquals("10", viewModel.getListData().get(0).getValue());
        assertEquals("0.4", viewModel.getListData().get(0).getProbability());
    }
    @Test
    public void canSetDataStatusEnterDataByDefolt() {
        assertEquals(DataStatus.WAITING.toString(), viewModel.getDataStatus());
    }
    @Test
    public void canSetDataStatusEnterDataWithEmptyData() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.9");
        viewModel.updateTableElement();

        viewModel.deleteTableElement(0);

        assertEquals(DataStatus.WAITING.toString(), viewModel.getDataStatus());
    }
    @Test
    public void canSetDataStatusBadFormat() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.1");
        viewModel.updateTableElement();

        assertEquals(DataStatus.BAD_FORMAT.toString(), viewModel.getDataStatus());
    }
    @Test
    public void canSetDataStatusBadFormatAfterDelete() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.9");
        viewModel.updateTableElement();
        viewModel.newValueProperty().set("20");
        viewModel.newProbabilityProperty().set("0.1");
        viewModel.updateTableElement();

        viewModel.deleteTableElement(1);

        assertEquals(DataStatus.BAD_FORMAT.toString(), viewModel.getDataStatus());
    }
    @Test
    public void canSetDataStatusReady() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1");
        viewModel.updateTableElement();

        assertEquals(DataStatus.READY.toString(), viewModel.getDataStatus());
    }
    @Test
    public void canSetDataStatusReadyAfterDelete() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.8");
        viewModel.updateTableElement();
        viewModel.newValueProperty().set("20");
        viewModel.newProbabilityProperty().set("0.2");
        viewModel.updateTableElement();
        viewModel.newValueProperty().set("30");
        viewModel.newProbabilityProperty().set("0.3");
        viewModel.updateTableElement();

        viewModel.deleteTableElement(2);

        assertEquals(DataStatus.READY.toString(), viewModel.getDataStatus());
    }
    @Test
    public void canSetOperationStatusWaitingDataByDefolt() {
        assertEquals(OperationStatus.WAITING_DATA.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusWaitingOperationAfterEnteringCorrectData() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        assertEquals(OperationStatus.WAITING_OPERATION.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusReadyAfterChoosingDispersion() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        viewModel.operationProperty().set(Operation.DISPERSION);
        viewModel.updateOperation();

        assertEquals(OperationStatus.READY.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusReadyAfterChoosingExpectedValue() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        viewModel.operationProperty().set(Operation.EXPECTED_VALUE);
        viewModel.updateOperation();

        assertEquals(OperationStatus.READY.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusWaitingDataAfterDelete() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.2");
        viewModel.updateTableElement();
        viewModel.newValueProperty().set("20");
        viewModel.newProbabilityProperty().set("0.8");
        viewModel.updateTableElement();

        viewModel.deleteTableElement(1);

        assertEquals(OperationStatus.WAITING_DATA.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusWaitingDataAfterAdd() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        viewModel.newValueProperty().set("20");
        viewModel.newProbabilityProperty().set("0.8");
        viewModel.updateTableElement();

        assertEquals(OperationStatus.WAITING_DATA.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusWaitingDataAfterUpdate() {
        viewModel.newValueProperty().set("100");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        viewModel.setSelectedElement(0);
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("0.8");
        viewModel.updateTableElement();

        assertEquals(OperationStatus.WAITING_DATA.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusWaitingDataAfterSetOperationAndUpdate() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        viewModel.operationProperty().set(Operation.DISPERSION);
        viewModel.updateOperation();
        viewModel.setSelectedElement(0);
        viewModel.newValueProperty().set("100");
        viewModel.newProbabilityProperty().set("0.8");
        viewModel.updateTableElement();

        assertEquals(OperationStatus.WAITING_DATA.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusWaitingParametrAfterChoosingRawMoment() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        viewModel.operationProperty().set(Operation.RAW_MOMENT);
        viewModel.updateOperation();

        assertEquals(OperationStatus.WAITING_PARAMETER.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canSetOperationStatusWaitingParameterAfterChoosingCentralMoment() {
        viewModel.newValueProperty().set("10");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();

        viewModel.operationProperty().set(Operation.CENTRAL_MOMENT);
        viewModel.updateOperation();

        assertEquals(OperationStatus.WAITING_PARAMETER.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canPushCalculateButtonWithCorrectInputAndOperation() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.DISPERSION);
        viewModel.updateOperation();

        assertFalse(viewModel.isCalculationDisabled());
    }
    @Test
    public void canNotPushCalculateButtonWithUncorrectInput() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("0.2");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.DISPERSION);
        viewModel.updateOperation();

        assertTrue(viewModel.isCalculationDisabled());
    }
    @Test
    public void canNotPushCalculateButtonWithoutOperationParameter() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("0.2");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.CENTRAL_MOMENT);
        viewModel.updateOperation();

        assertTrue(viewModel.isCalculationDisabled());
    }
    @Test
    public void canSetParametersPartVisibleWithDataAndCentralMoment() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.CENTRAL_MOMENT);
        viewModel.updateOperation();

        assertTrue(viewModel.isEnterParameterVisible());
    }
    @Test
    public void canSetParametersPartVisibleWithDataAndRawMoment() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.RAW_MOMENT);
        viewModel.updateOperation();

        assertTrue(viewModel.isEnterParameterVisible());
    }
    @Test
    public void canSetParametersPartInvisibleWithDataAndDispersion() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.RAW_MOMENT);
        viewModel.updateOperation();

        viewModel.operationProperty().set(Operation.DISPERSION);
        viewModel.updateOperation();

        assertFalse(viewModel.isEnterParameterVisible());
    }
    @Test
    public void canSetParametersPartInvisibleAfterUpdateData() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.RAW_MOMENT);
        viewModel.updateOperation();

        viewModel.setSelectedElement(0);
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("0.8");
        viewModel.updateTableElement();

        assertFalse(viewModel.isEnterParameterVisible());
    }
    @Test
    public void canSetOperationParameter() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.CENTRAL_MOMENT);
        viewModel.updateOperation();

        viewModel.operationParameterProperty().set("2");

        assertEquals(OperationStatus.READY.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canNotSetErrorOperationParameter() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.RAW_MOMENT);
        viewModel.updateOperation();

        viewModel.operationParameterProperty().set("error");

        assertEquals(OperationStatus.BAD_FORMAT.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canNotSetOperationParameterLess0() {
        viewModel.newValueProperty().set("2");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.RAW_MOMENT);
        viewModel.updateOperation();

        viewModel.operationParameterProperty().set("-1");

        assertEquals(OperationStatus.BAD_FORMAT.toString(), viewModel.getOperationStatus());
    }
    @Test
    public void canCalculateExpectedValue() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("1.0");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.EXPECTED_VALUE);
        viewModel.updateOperation();

        viewModel.calculate();

        assertEquals("1.0", viewModel.getResult());
    }
    @Test
    public void canCalculateRawMoment() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("0.5");
        viewModel.updateTableElement();
        viewModel.newValueProperty().set("5");
        viewModel.newProbabilityProperty().set("0.5");
        viewModel.updateTableElement();
        viewModel.operationProperty().set(Operation.RAW_MOMENT);
        viewModel.updateOperation();
        viewModel.operationParameterProperty().set("1");

        viewModel.calculate();

        assertEquals("3.0", viewModel.getResult());
    }
    @Test
    public void canResetSelectedElement() {
        viewModel.newValueProperty().set("1");
        viewModel.newProbabilityProperty().set("0.5");
        viewModel.updateTableElement();
        viewModel.setSelectedElement(0);

        viewModel.resetSelectedElement();

        assertEquals(InputDataStatus.WAITING.toString(), viewModel.getInputDataStatus());
        assertEquals("", viewModel.newValueProperty().get());
        assertEquals("", viewModel.newProbabilityProperty().get());
    }
    @Test
    public void canSetDoubleValues() {
        viewModel.newValueProperty().set("-2.0");
        viewModel.newProbabilityProperty().set("0.5");

        viewModel.updateTableElement();

        assertEquals("-2.0", viewModel.getListData().get(0).getValue());
    }
}
