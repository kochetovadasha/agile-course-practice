package ru.unn.agile.complexnumbercalculator.infrastructure;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RealCalendar implements ICalendar {
    @Override
    public String getTime() {
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(cal.getTime());
    }
}
