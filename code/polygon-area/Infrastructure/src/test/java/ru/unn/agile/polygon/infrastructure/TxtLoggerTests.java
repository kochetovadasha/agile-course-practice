package ru.unn.agile.polygon.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private  final String LOG_FILE_PATH = "./TxtLogger_Tests-lab3_polygon_area_calc.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(LOG_FILE_PATH);
    }

    @After
    public void tearDown() {
        txtLogger = null;
    }

    @Test
    public void isLogFileCreated() {
        File f = new File(LOG_FILE_PATH);
        assertTrue(f.exists() && !f.isDirectory());
    }

    @Test
    public void isLogMessageAdded() {
        String testMessage = "Test log message";
        txtLogger.log(testMessage);
        assertTrue(txtLogger.getLog().get(0).contains(testMessage));
    }
}