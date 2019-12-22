package ru.unn.agile.complexnumbercalculator.viewmodel;

import java.util.List;

public interface ILogger {
    void writeToLog(String inputString);

    List<String> getLog();
}
