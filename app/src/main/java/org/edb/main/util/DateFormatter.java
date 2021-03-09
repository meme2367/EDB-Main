package org.edb.main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static Date getSimpleFormattedDateFromDate(Date anotherDate) throws ParseException{
        SimpleDateFormat fm = getSimpleDateFormat();

        String formattedStrCurTime = fm.format(anotherDate);
        Date tempTime;
        tempTime = fm.parse(formattedStrCurTime);

        return tempTime;
    }

    public static Date getSimpleFormattedDateFromString(String stringDate) throws ParseException {
        SimpleDateFormat fm = getSimpleDateFormat();

        Date tempDate = fm.parse(stringDate);
        return tempDate;
    }

    public static String getSimpleFormattedStringFromDate(Date date){
        SimpleDateFormat fm = getSimpleDateFormat();

        return fm.format(date);
    }

    private static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
