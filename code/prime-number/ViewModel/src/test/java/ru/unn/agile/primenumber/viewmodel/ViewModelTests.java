package ru.unn.agile.primenumber.viewmodel;

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
    public void checkStartElemDefaultValue() {
        assertEquals("", viewModel.startElemProperty().get());
    }

    @Test
    public void checkEndElemDefaultValue() {
        assertEquals("", viewModel.endElemProperty().get());
    }

    @Test
    public void checkOutputFieldDefaultValues() {
        assertEquals("", viewModel.outputProperty().get());
    }

    @Test
    public void checkDefaultStatus() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.findPrimaryNums();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        viewModel.startElemProperty().set("1");
        viewModel.endElemProperty().set("10");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.startElemProperty().set("a");
        viewModel.endElemProperty().set("b");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfValuesAreNotSet() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.findBtnDisabledProperty().get());
    }

    @Test
    public void findButtonIsDisabledWhenReportBadFormat() {
        viewModel.startElemProperty().set("null");
        viewModel.endElemProperty().set("NaN");

        assertTrue(viewModel.findBtnDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.startElemProperty().set("1");

        assertTrue(viewModel.findBtnDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        viewModel.startElemProperty().set("1");
        viewModel.endElemProperty().set("4");

        assertFalse(viewModel.findBtnDisabledProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        viewModel.startElemProperty().set("1");
        viewModel.endElemProperty().set("4");

        viewModel.findPrimaryNums();

        assertEquals("[2, 3]", viewModel.outputProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.startElemProperty().set("1");
        viewModel.endElemProperty().set("2");

        viewModel.findPrimaryNums();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperValues() {
        viewModel.startElemProperty().set("1");
        viewModel.endElemProperty().set("2");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }
}
