package ru.unn.agile.stack.infrastructure;

import ru.unn.agile.stack.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TxtLogger implements ILogger {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final String filename;
    private final BufferedWriter writer;

    public TxtLogger(final String filename) {
        this.filename = filename;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        writer = logWriter;
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }

    @Override
    public void log(final String message) {
        try {
            writer.write(now() + " > " + message);
            writer.newLine();
            writer.flush();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
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
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        return log;
    }
}
