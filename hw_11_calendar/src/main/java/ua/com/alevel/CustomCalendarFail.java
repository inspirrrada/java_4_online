package ua.com.alevel;

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


public class CustomCalendarFail {
    private Calendar calendar;
    private static final String FORMAT_DAYS = "yyyy-MM-dd";
    private static final String FORMAT_MINUTES = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss SSS";
    private static final String[] POSSIBLE_FORMATS = new String[]{FORMAT_DAYS, FORMAT_MINUTES, FORMAT_SECONDS, FORMAT_MILLIS};

    public CustomCalendarFail() {
        this.calendar = Calendar.getInstance();
    }

    public CustomCalendarFail(String format) {
        String matchedFormat = getMatchedFormat(format);
            try {
                Date date = getDateFromString(matchedFormat, format);
                LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                if (matchedFormat.equals(FORMAT_DAYS)) {
                    localDateTime = localDateTime.withHour(0).withMinute(0).withSecond(0);
                }
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

    public CustomCalendarFail(long time) {
//        if (time == 0) {
//            this.calendar.set(Calendar.YEAR, 0);
//            this.calendar.set(Calendar.MONTH, 0);
//            this.calendar.set(Calendar.DATE, 0);
//        } else {
//
//        }

//        if (this.calendar.get(YEAR) < 1970) {
//            this.calendar.setTimeInMillis(time - 62167399200000L);  //62167399200000 -> millis before start unix-time
//        } else {
//            this.calendar.setTimeInMillis(time);
//        }
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(time - 62167399200000L); //62167399200000 -> millis before start unix-time
//        this.calendar.setTimeInMillis(time - 62167485600000L);
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

    public void addDate(CustomCalendarFail date) {
//        long thisMillis = this.getTimeMillis();
//        long dateMillis = date.getTimeMillis();
//        long sum = thisMillis + dateMillis;
//        this.calendar.setTimeInMillis(sum);
//        System.out.println(new Date(0));
//        String myDate1 = "0001-01-01";
//        String myDate2 = "0000-01-00";
//        String myDate3 = "0000-00-00";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date test1 = sdf.parse(myDate1);
//            Date test2 = sdf.parse(myDate2);
//            Date test3 = sdf.parse(myDate3);
//            System.out.println("0001-01-01, millis: " + test1.getTime());
//            System.out.println("0000-01-00, millis: " + test2.getTime());
//            System.out.println("0000-00-00, millis: " + test3.getTime());
////            System.out.println("date of 1577829600000: " + new Date(1577829600000L));
////            System.out.println("date of 1602277200000L: " + new Date(1602277200000L));
////            System.out.println("date of 3180106800000L: " + new Date(3180106800000L));
//            Duration d = Duration.ofMinutes(10);
//            CustomCalendar cc1 = new CustomCalendar(-62170164000000L);
//            Calendar cal1 = Calendar.getInstance();
//            cal1.setTimeInMillis(-62170164000000L);
//            System.out.println("cc1: " + cc1);
//            System.out.println("cal1: " + cal1.YEAR + "-" + cal1.MONTH + "-" + cal1.DAY_OF_MONTH);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        long millis = date.getTime();
        Calendar c1 = this.calendar;
        Calendar c2 = date.calendar;
        Calendar cTotal = (Calendar) c1.clone();
        cTotal.add(Calendar.YEAR, c2.get(Calendar.YEAR));
        cTotal.add(Calendar.MONTH, c2.get(Calendar.MONTH) + 1);
        cTotal.add(Calendar.DATE, c2.get(DATE));
        cTotal.add(Calendar.DAY_OF_MONTH, c2.get(DAY_OF_MONTH));
        cTotal.add(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
        cTotal.add(Calendar.MINUTE, c2.get(Calendar.MINUTE));
        cTotal.add(Calendar.SECOND, c2.get(Calendar.SECOND));
        cTotal.add(Calendar.MILLISECOND, c2.get(Calendar.MILLISECOND));
        this.calendar = cTotal;
    }

    public void addDate(String format) {
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59 999");
//        String str1 = sdf2.format(this.calendar.getTime());
//        System.out.println("str1: " + str1);
//        this.set(str1);
//        String matchedFormat = getMatchedFormat(format);
//        if (matchedFormat.equals(FORMAT_DAYS)) {
//            format += " 23:59:59 999";
//        }
        System.out.println("this: " + this);
        CustomCalendarFail calendarForAdd = new CustomCalendarFail(format);
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

    public void minusDate(CustomCalendarFail date) {
        long thisMilles = this.calendar.getTimeInMillis();
        long dateMillis = date.getTimeMillis();
        long diff = thisMilles - dateMillis;
        this.calendar.setTime(new Date(thisMilles - diff));
    }

    public void minusDate(String format) {
        CustomCalendarFail calForMinus = new CustomCalendarFail(format);
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

    public static int differenceInYears(CustomCalendarFail first, CustomCalendarFail second) {
        return first.calendar.get(YEAR) - second.calendar.get(YEAR);
    }

    public static int differenceInMonths(CustomCalendarFail first, CustomCalendarFail second) {
        return first.calendar.get(MONTH) - second.calendar.get(MONTH);
    }

    public static int differenceInDays(CustomCalendarFail first, CustomCalendarFail second) {
        return differenceInHours(first, second) / 24;
    }

    public static int differenceInHours(CustomCalendarFail first, CustomCalendarFail second) {
        return (int) differenceInMinutes(first, second) / 60;
    }

    public static long differenceInMinutes(CustomCalendarFail first, CustomCalendarFail second) {
        return differenceInSeconds(first, second) / 60;
    }

    public static long differenceInSeconds(CustomCalendarFail first, CustomCalendarFail second) {
        return differenceInMilliseconds(first, second) / 1000;
    }

    public static long differenceInMilliseconds(CustomCalendarFail first, CustomCalendarFail second) {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEEE dd MMMMM yyyy HH:mm:ss.SSSZ");
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
            String value = this.calendar.get(YEAR) + "-" + this.calendar.get(MONTH + 1) + "-" + this.calendar.get(DAY_OF_MONTH) + " " +
                    this.calendar.get(HOUR_OF_DAY) + ":" + this.calendar.get(MINUTE) + ":" + this.calendar.get(SECOND) + " " + this.calendar.get(MILLISECOND);
            return value;
//            dateFormat.format(this.calendar.getTime());
        } else {
            return "";
        }
    }
}
