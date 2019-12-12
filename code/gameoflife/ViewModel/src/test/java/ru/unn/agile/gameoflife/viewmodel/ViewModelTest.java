package ru.unn.agile.gameoflife.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTest {
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
    public void defaultHeightIsEmpty() {
        assertEquals("", viewModel.heightFieldProperty().get());
    }

    @Test
    public void defaultWidthIsEmpty() {
        assertEquals("", viewModel.widthFieldProperty().get());
    }

    @Test
    public void defaultStatusIsWaiting() {
        assertEquals(Status.WAITING.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsWaitingWhenFieldsAreEmpty() {
        assertEquals(Status.WAITING.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreNotEmpty() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");

        assertEquals(Status.READY.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsWaitingWhenFieldsWasDeleted() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.heightFieldProperty().set("");
        viewModel.widthFieldProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.statusTextProperty().get());
    }
}
