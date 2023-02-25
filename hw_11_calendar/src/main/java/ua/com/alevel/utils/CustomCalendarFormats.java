package ua.com.alevel.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomCalendarFormats {
    private static final String FORMAT_DAYS = "yyyy-MM-dd";
    private static final String FORMAT_MINUTES = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String[] POSSIBLE_FORMATS = new String[]{FORMAT_DAYS, FORMAT_MINUTES, FORMAT_SECONDS, FORMAT_MILLIS};

    private static boolean hasValidDateFormat(String pattern, String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isFormatValid(String dateValue) {
        boolean isFormatValid = false;
        for (String possibleFormat : CustomCalendarFormats.POSSIBLE_FORMATS) {
            if (hasValidDateFormat(possibleFormat, dateValue)) {
                isFormatValid = true;
                break;
            }
        }
        return isFormatValid;
    }
}
