package ru.unn.agile.complexnumbercalculator.infrastructure;

import ru.unn.agile.complexnumbercalculator.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private final String filename;
    private final BufferedWriter writer;
    private final ICalendar calendar;
    //private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //private static String getCurrentTime() {
    // Calendar cal = Calendar.getInstance();
    //    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    //    return sdf.format(cal.getTime());
    //}

    public TxtLogger(final String filename, final ICalendar calendar) {
        this.filename = filename;
        this.calendar = calendar;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void addToLog(String inputString) {
        try {
            writer.write(calendar.getTime() + " > " + inputString);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

}
