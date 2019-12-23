package ru.unn.agile.vectoroperations.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TxtLoggerTests {
    private static final String FILE_NAME = "./ViewModelTxt.log";
    private TxtLogger txtLogger;

    @Before
    public void beforeStart() {
        txtLogger = new TxtLogger(FILE_NAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException exp) {
            fail("File " + FILE_NAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteOneSimpleLogMessage() {
        String testMessage = "Yo yo yo! 1483 to the 3 to the 3 to the 6 to the 9,"
                             + " representing the ABQ";

        txtLogger.log(testMessage);

        String logMessage = txtLogger.getLog().get(0);
        assertTrue(logMessage.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteTwoLogMessages() {
        String testMessageOne = "Yo yo yo! 1483 to the 3 to the 3 to the 6 to the 9,"
                                + " representing the ABQ";
        String testMessageTwo = "What up! Leave at the tone";
        int firstMessageIdx = 0;
        int secondMessageIdx = 1;
        txtLogger.log(testMessageOne);
        txtLogger.log(testMessageTwo);

        List<String> logMessages = txtLogger.getLog();
        String logMessageOne = logMessages.get(firstMessageIdx);
        String logMessageTwo = logMessages.get(secondMessageIdx);
        assertTrue(logMessageOne.matches(".*" + testMessageOne + "$"));
        assertTrue(logMessageTwo.matches(".*" + testMessageTwo + "$"));
    }
}
