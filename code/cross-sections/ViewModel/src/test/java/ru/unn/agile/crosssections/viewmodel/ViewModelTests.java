package ru.unn.agile.crosssections.viewmodel;

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
    public void canSetDefaults() {
        assertEquals("", viewModel.fstSectionStartXProperty().get());
        assertEquals("", viewModel.fstSectionStartYProperty().get());
        assertEquals("", viewModel.fstSectionFinishXProperty().get());
        assertEquals("", viewModel.fstSectionFinishYProperty().get());
        assertEquals("", viewModel.sndSectionStartXProperty().get());
        assertEquals("", viewModel.sndSectionStartYProperty().get());
        assertEquals("", viewModel.sndSectionFinishXProperty().get());
        assertEquals("", viewModel.sndSectionFinishYProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputCrossedData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.check();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.fstSectionStartXProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.fstSectionStartXProperty().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.checkDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputCrossedData();
        viewModel.fstSectionStartXProperty().set("trash");

        assertTrue(viewModel.checkDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.fstSectionStartXProperty().set("1");

        assertTrue(viewModel.checkDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputCrossedData();

        assertFalse(viewModel.checkDisabledProperty().get());
    }

    @Test
    public void operationWhenCrossedHasCorrectResult() {
        setInputCrossedData();
        viewModel.check();
        assertEquals("Crossed", viewModel.resultProperty().get());
    }

    @Test
    public void operationWhenDontCrossedHasCorrectResult() {
        setInputDontCrossedData();
        viewModel.check();
        assertEquals("Don't Crossed", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputCrossedData();
        viewModel.check();
        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.fstSectionStartXProperty().set("word");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputCrossedData();
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    private void setInputCrossedData() {
        viewModel.fstSectionStartXProperty().set("0");
        viewModel.fstSectionStartYProperty().set("0");
        viewModel.fstSectionFinishXProperty().set("1");
        viewModel.fstSectionFinishYProperty().set("1");
        viewModel.sndSectionStartXProperty().set("1");
        viewModel.sndSectionStartYProperty().set("0");
        viewModel.sndSectionFinishXProperty().set("0");
        viewModel.sndSectionFinishYProperty().set("1");
    }

    private void setInputDontCrossedData() {
        viewModel.fstSectionStartXProperty().set("0");
        viewModel.fstSectionStartYProperty().set("0");
        viewModel.fstSectionFinishXProperty().set("1");
        viewModel.fstSectionFinishYProperty().set("0");
        viewModel.sndSectionStartXProperty().set("1");
        viewModel.sndSectionStartYProperty().set("1");
        viewModel.sndSectionFinishXProperty().set("0");
        viewModel.sndSectionFinishYProperty().set("1");
    }
}
