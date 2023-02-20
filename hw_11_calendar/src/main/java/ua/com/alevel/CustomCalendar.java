package ua.com.alevel;

import ua.com.alevel.utils.ColorUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;
import static java.util.Calendar.*;


public class CustomCalendar {
    private Calendar calendar;
    private static final String FORMAT_DAYS = "yyyy-MM-dd";
    private static final String FORMAT_MINUTES = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss SSS";
    private static final String[] POSSIBLE_FORMATS = new String[]{FORMAT_DAYS, FORMAT_MINUTES, FORMAT_SECONDS, FORMAT_MILLIS};

    public CustomCalendar() {
        this.calendar = Calendar.getInstance();
    }

    public CustomCalendar(String format) {
        String matchedFormat = getMatchedFormat(format);
            try {
                Date date = getDateFromString(matchedFormat, format);
                LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                this.calendar = Calendar.getInstance();
                int year = localDateTime.getYear();
                int month = localDateTime.getMonthValue() - 1;
                int monthDay = localDateTime.getDayOfMonth();
                int hours = localDateTime.getHour();
                int minutes = localDateTime.getMinute();
                int seconds = localDateTime.getSecond();
                long milliseconds = localDateTime.get(ChronoField.MILLI_OF_SECOND);
                this.set(year, month, monthDay, hours, minutes, seconds, (int) milliseconds);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
    }

    public CustomCalendar(long time) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(time - 62167399200000L); //62167399200000 -> millis before start unix-time
    }

    public String now() {
        return convertDateToPrintFormat(Calendar.getInstance().getTime());
    }

    public long getTimeMillis() {
        if (this.calendar.get(YEAR) < 1970) {
            return this.calendar.getTimeInMillis() + 62167399200000L; //62167399200000 -> millis before start unix-time
        } else {
            return this.calendar.getTimeInMillis();
        }
    }

    public void set(int year, int month) {
        this.calendar.set(Calendar.YEAR, year);
        this.calendar.set(Calendar.MONTH, month);
    }

    public void set(int year, int month, int day, int hour, int minutes) {
        this.set(year, month);
        this.calendar.set(Calendar.DAY_OF_MONTH, day);
        this.calendar.set(Calendar.HOUR_OF_DAY, hour);
        this.calendar.set(Calendar.MINUTE, minutes);
    }

    public void set(int year, int month, int day, int hour, int minutes, int seconds, int milliseconds) {
        this.set(year, month, day, hour, minutes);
        this.calendar.set(Calendar.SECOND, seconds);
        this.calendar.set(Calendar.MILLISECOND, milliseconds);
    }

    public void set(String format) {
        String matchedFormat = getMatchedFormat(format);
            try {
                this.calendar.setTime(getDateFromString(matchedFormat, format));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
    }

    public void addDate(CustomCalendar date) {
        long thisMillis = this.calendar.getTimeInMillis();
        long dateMillis = date.getTimeMillis();
        long diff = thisMillis - dateMillis;
        this.calendar.setTime(new Date(thisMillis + diff));
    }

    public void addDate(String format) {
        CustomCalendar calendarForAdd = new CustomCalendar(format);
        this.addDate(calendarForAdd);
    }

    public void addYears(int years) {
        this.calendar.add(YEAR, years);
    }

    public void addMonths(int months) {
        this.calendar.add(MONTH, months + 1);
    }

    public void addDays(int days) {
        this.calendar.add(DAY_OF_YEAR, days);
    }

    public void addHours(int hours) {
        this.calendar.add(HOUR, hours);
    }

    public void addMinutes(int minutes) {
        this.calendar.add(MINUTE, minutes);
    }

    public void addSeconds(int seconds) {
        this.calendar.add(SECOND, seconds);
    }

    public void addMilliseconds(int milliseconds) {
        this.calendar.add(MILLISECOND, milliseconds);
    }

    public void minusDate(CustomCalendar date) {
        long thisMilles = this.calendar.getTimeInMillis();
        long dateMillis = date.getTimeMillis();
        long diff = thisMilles - dateMillis;
        this.calendar.setTime(new Date(thisMilles - diff));
    }

    public void minusDate(String format) {
        CustomCalendar calForMinus = new CustomCalendar(format);
        this.minusDate(calForMinus);
    }

    public void minusYears(int years) {
        this.calendar.add(YEAR, (-1 * years));
    }

    public void minusMonths(int months) {
        this.calendar.add(MONTH, (-1 * months));
    }

    public void minusDays(int days) {
        this.calendar.add(DAY_OF_YEAR, (days * -1));
    }

    public void minusHours(int hours) {
//        minusMinutes(hours * 60);
        this.calendar.add(HOUR, (hours * -1));
    }

    public void minusMinutes(int minutes) {
        minusSeconds(minutes * 60);
    }

    public void minusSeconds(int seconds) {
        minusMilliseconds(seconds * 1000);
    }

    public void minusMilliseconds(int milliseconds) {
        this.calendar.add(MILLISECOND, (milliseconds * -1));
    }

    public static int differenceInYears(CustomCalendar first, CustomCalendar second) {
        return first.calendar.get(YEAR) - second.calendar.get(YEAR);
    }

    public static int differenceInMonths(CustomCalendar first, CustomCalendar second) {
        return first.calendar.get(MONTH) - second.calendar.get(MONTH);
    }

    public static int differenceInDays(CustomCalendar first, CustomCalendar second) {
        return differenceInHours(first, second) / 24;
    }

    public static int differenceInHours(CustomCalendar first, CustomCalendar second) {
        return (int) differenceInMinutes(first, second) / 60;
    }

    public static long differenceInMinutes(CustomCalendar first, CustomCalendar second) {
        return differenceInSeconds(first, second) / 60;
    }

    public static long differenceInSeconds(CustomCalendar first, CustomCalendar second) {
        return differenceInMilliseconds(first, second) / 1000;
    }

    public static long differenceInMilliseconds(CustomCalendar first, CustomCalendar second) {
        return first.calendar.getTimeInMillis() - second.calendar.getTimeInMillis();
    }

    /**
     * additional methods
     */

    public static boolean isFormatValid(String dateValue) {
        boolean isFormatValid = false;
        for (String possibleFormat : POSSIBLE_FORMATS) {
            if (hasValidDateFormat(possibleFormat, dateValue)) {
                isFormatValid = true;
                break;
            }
        }
        return isFormatValid;
    }

    private String getMatchedFormat(String dateValue) {
        String matchedFormat = "";
        for (String possibleFormat : POSSIBLE_FORMATS) {
            boolean isValid = hasValidDateFormat(possibleFormat, dateValue);
            if (isValid) {
                matchedFormat = possibleFormat;
            }
        }
        return matchedFormat;
    }

    private Date getDateFromString(String format, String dateValue) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(dateValue);
    }

    private String convertDateToPrintFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_MILLIS);
        return simpleDateFormat.format(date);
    }

    private Date getSetDate() {
        return this.calendar.getTime();
    }

    public String getDetailedInfoOfSetDate() {
        Date date = this.getSetDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss.SSSZ");
        return simpleDateFormat.format(date);
    }

    private static boolean hasValidDateFormat(String pattern, String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        if (this.calendar != null) {
            return dateFormat.format(this.calendar.getTime());
        } else {
            return "";
        }
    }
}
