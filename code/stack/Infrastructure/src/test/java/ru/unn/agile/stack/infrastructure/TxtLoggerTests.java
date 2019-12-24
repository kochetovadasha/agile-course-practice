package ru.unn.agile.stack.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String FILE_NAME = "./TxtLoggerTests.log";
    private static final String LOG_PATTERN = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*";

    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILE_NAME);
    }

    @After
    public void tearDown() {
        txtLogger = null;
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException ex) {
            fail("File " + FILE_NAME + " was not found!");
        }
    }

    @Test
    public void areDateAndTimeContainedInLog() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String logMessage = txtLogger.getLog().get(0);
        assertTrue(logMessage.matches(LOG_PATTERN));
    }

    @Test
    public void canWriteOneLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String logMessage = txtLogger.getLog().get(0);
        assertTrue(logMessage.matches(".*" + logMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessages() {
        String[] testMessages = {"Test message 1", "Test message 2"};

        for (String testMessage : testMessages) {
            txtLogger.log(testMessage);
        }

        List<String> logMessages = txtLogger.getLog();
        for (int i = 0; i < logMessages.size(); i++) {
            String logMessage = logMessages.get(i);
            assertTrue(logMessage.matches(".*" + logMessage + "$"));
        }
    }
}
