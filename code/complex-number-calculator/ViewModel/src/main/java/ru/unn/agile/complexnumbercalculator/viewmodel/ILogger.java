package ru.unn.agile.complexnumbercalculator.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String inputString);

    List<String> getLog();
}
