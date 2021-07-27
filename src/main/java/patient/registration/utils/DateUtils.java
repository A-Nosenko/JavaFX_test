package patient.registration.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {
    }

    private static final String PATTERN = "yyyy.MM.dd HH:mm";

    public static String convertDateToText(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
        return dateFormat.format(date);
    }

    public static Date convertTextToDate(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
        try {
            return dateFormat.parse(text);
        } catch (ParseException exception) {
            System.err.println(exception.toString());
            return null;
        }
    }
}
