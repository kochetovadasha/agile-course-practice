package ru.unn.agile.stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canCreateViewModelWithoutLogger() {
        ViewModel newViewModel = new ViewModel();

        assertNotNull(newViewModel);
    }

    @Test
    public void canCreateViewModelWithNotEmptyLogger() {
        ViewModel newViewModel = new ViewModel(new FakeLogger());

        assertNotNull(newViewModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateViewModelWithNullLogger() {
        ViewModel newViewModel = new ViewModel(null);
    }

    @Test
    public void canSetDefaultLogValue() {
        assertEquals(0, viewModel.getLogList().size());
    }

    @Test
    public void logIsCorrectWhenPushElement() {
        Double element = 11.0;
        viewModel.setPushElement(Double.toString(element));

        viewModel.pushNewElement();

        String expectedLogMessage = viewModel.getLogList().get(0);
        assertTrue(expectedLogMessage.matches("(.*)" + element + "(.*)"));
    }

    @Test
    public void logIsCorrectWhenPopElement() {
        Double element = 10.0;
        viewModel.setPushElement(Double.toString(element));
        viewModel.pushNewElement();

        viewModel.popElement();

        String expectedLogMessage = viewModel.getLogList().get(1);
        assertTrue(expectedLogMessage.matches("(.*)" + element + "(.*)"));
    }

    @Test
    public void logIsCorrectWhenPushNonValidElement() {
        String element = "!";
        viewModel.setPushElement(element);

        viewModel.pushNewElement();

        String expectedLogMessage = viewModel.getLogList().get(0);
        assertTrue(expectedLogMessage.matches("(.*)" + element + "(.*)"));
    }

    @Test
    public void checkDefaultEmptyStatus() {
        var emptyStatus = "Stack is empty";
        assertEquals(emptyStatus, viewModel.getIsStackEmptyInfo());
    }

    @Test
    public void checkDefaultStackSize() {
        var defaultSize = "0";
        assertEquals(defaultSize, viewModel.getStackSize());
    }

    @Test
    public void checkDefaultTopElement() {
        var defaultTopElement = "None";
        assertEquals(defaultTopElement, viewModel.getTopElement());
    }

    @Test
    public void checkDefaultPopElement() {
        var defaultPopElement = "None";
        assertEquals(defaultPopElement, viewModel.getPopElement());
    }

    @Test
    public void checkDefaultPushElement() {
        var defaultPushElement = "";
        assertEquals(defaultPushElement, viewModel.getPushElement());
    }

    @Test
    public void checkDefaultStatus() {
        var defaultStatus = "Waiting for new element";
        assertEquals(defaultStatus, viewModel.getStatus());
    }

    @Test
    public void checkDefaultPopButtonState() {
        assertFalse(viewModel.getPopButtonState());
    }

    @Test
    public void canSetDefaultTextLog() {
        assertEquals("", viewModel.getTextLog());
    }

    @Test
    public void correctDefaultTextLog() {
        assertEquals("", viewModel.textLogProperty().get());
    }

    @Test
    public void canPushNewElement() {
        viewModel.setPushElement("5");
        viewModel.pushNewElement();

        var stackSize = "1";
        var notNoneTopElement = "5.0";
        var pushElement = "5";

        assertEquals(stackSize, viewModel.getStackSize());
        assertEquals(notNoneTopElement, viewModel.getTopElement());
        assertEquals(pushElement, viewModel.getPushElement());
    }

    @Test
    public void stackIsNotEmptyAfterPush() {
        viewModel.setPushElement("5");
        viewModel.pushNewElement();

        var stackEmptyStatus = "Stack is not empty";
        assertEquals(stackEmptyStatus, viewModel.getIsStackEmptyInfo());
    }

    @Test
    public void checkReadyStatusAfterPush() {
        viewModel.setPushElement("5");
        viewModel.pushNewElement();

        var status = "Ready to push new element";
        assertEquals(status, viewModel.getStatus());
    }

    @Test
    public void canNotPushInvalidFormat() {
        viewModel.setPushElement("k");
        viewModel.pushNewElement();

        var stackSize = "0";
        var noneTopElement = "None";
        var pushElement = "k";

        assertEquals(stackSize, viewModel.getStackSize());
        assertEquals(noneTopElement, viewModel.getTopElement());
        assertEquals(pushElement, viewModel.getPushElement());
    }

    @Test
    public void checkIsEmptyStatusAfterFailedPush() {
        viewModel.setPushElement("k");
        viewModel.pushNewElement();

        var stackEmptyStatus = "Stack is empty";
        assertEquals(stackEmptyStatus, viewModel.getIsStackEmptyInfo());
    }

    @Test
    public void checkBadInputStatusAfterFailedPush() {
        viewModel.setPushElement("k");
        viewModel.pushNewElement();

        var status = "Invalid format of the pushing element";
        assertEquals(status, viewModel.getStatus());
    }

    @Test
    public void canNotPushEmptyElement() {
        viewModel.setPushElement("");
        viewModel.pushNewElement();

        var stackSize = "0";
        var noneTopElement = "None";
        var emptyPushElement = "";

        assertEquals(stackSize, viewModel.getStackSize());
        assertEquals(noneTopElement, viewModel.getTopElement());
        assertEquals(emptyPushElement, viewModel.getPushElement());
    }

    @Test
    public void checkIsEmptyAfterPushEmptyElement() {
        viewModel.setPushElement("");
        viewModel.pushNewElement();

        var stackEmptyStatus = "Stack is empty";
        assertEquals(stackEmptyStatus, viewModel.getIsStackEmptyInfo());
    }

    @Test
    public void checkWaitingStatusAfterPushEmptyElement() {
        viewModel.setPushElement("");
        viewModel.pushNewElement();

        var status = "Waiting for new element";
        assertEquals(status, viewModel.getStatus());
    }

    @Test
    public void canPopElement() {
        viewModel.setPushElement("1");
        viewModel.pushNewElement();
        viewModel.popElement();

        var stackSize = "0";
        var noneTopElement = "None";
        var notNonePopElement = "1.0";

        assertEquals(stackSize, viewModel.getStackSize());
        assertEquals(noneTopElement, viewModel.getTopElement());
        assertEquals(notNonePopElement, viewModel.getPopElement());
    }

    @Test
    public void checkIsEmptyStatusAfterOnePop() {
        viewModel.setPushElement("1");
        viewModel.pushNewElement();
        viewModel.popElement();

        var stackEmptyStatus = "Stack is empty";

        assertEquals(stackEmptyStatus, viewModel.getIsStackEmptyInfo());
    }

    @Test
    public void checkReadyStatusAfterOnePop() {
        viewModel.setPushElement("1");
        viewModel.pushNewElement();
        viewModel.popElement();

        var status = "Ready to push new element";

        assertEquals(status, viewModel.getStatus());
    }

    @Test
    public void canNotPopElementFromEmptyStack() {
        viewModel.popElement();

        var nullStackSize = "0";
        var nonePopElement = "None";

        assertEquals(nullStackSize, viewModel.getStackSize());
        assertEquals(nonePopElement, viewModel.getPopElement());
    }

    @Test
    public void isPopButtonEnabledForNotEmptyStack() {
        viewModel.setPushElement("1.0");
        viewModel.pushNewElement();
        assertTrue(viewModel.getPopButtonState());
    }

    @Test
    public void isPopButtonDisabledForEmptyStack() {
        viewModel.setPushElement("1.0");
        viewModel.pushNewElement();
        viewModel.popElement();
        assertFalse(viewModel.getPopButtonState());
    }

    @Test
    public void isPopButtonDisabledAfterFailedPush() {
        viewModel.setPushElement("k");
        viewModel.pushNewElement();
        assertFalse(viewModel.getPopButtonState());
    }

    @Test
    public void canWorkCorrectlyWithSeveralPush() {
        viewModel.setPushElement("1.0");
        viewModel.setPushElement("2.0");
        viewModel.setPushElement("3.0");
        viewModel.pushNewElement();
        viewModel.pushNewElement();
        viewModel.pushNewElement();

        var stackSize = "3";
        var notNoneTopElement = "3.0";
        var notNullPushElement = "3.0";

        assertEquals(stackSize, viewModel.getStackSize());
        assertEquals(notNoneTopElement, viewModel.getTopElement());
        assertEquals(notNullPushElement, viewModel.getPushElement());
    }

    @Test
    public void canWorkCorrectlyWithSeveralPop() {
        viewModel.setPushElement("1.0");
        viewModel.pushNewElement();
        viewModel.setPushElement("2.0");
        viewModel.pushNewElement();
        viewModel.setPushElement("3.0");
        viewModel.pushNewElement();
        viewModel.popElement();
        viewModel.popElement();

        var stackSize = "1";
        var notNoneTopElement = "1.0";
        var notNonePopElement = "2.0";
        var notNullPushElement = "3.0";

        assertEquals(stackSize, viewModel.getStackSize());
        assertEquals(notNoneTopElement, viewModel.getTopElement());
        assertEquals(notNonePopElement, viewModel.getPopElement());
        assertEquals(notNullPushElement, viewModel.getPushElement());
    }

    @Test
    public void logIsCorrectWhenPushingEmptyElement() {
        String textLog = "Pushing element is empty";
        viewModel.setPushElement("");

        viewModel.pushNewElement();

        String logMessage = viewModel.getLogList().get(0);
        assertTrue(logMessage.matches("(.*)" + textLog + "(.*)"));
    }
}
