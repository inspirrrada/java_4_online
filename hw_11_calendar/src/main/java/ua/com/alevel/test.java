package ua.com.alevel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.util.Calendar.*;


public class test {
    private Calendar calendar;

    private static ua.com.alevel.CustomCalendar instance;

    public test() {
        this.calendar = Calendar.getInstance();
    }

    public test(String format) {
        //this.calendar = Calendar.getInstance();
        List<SimpleDateFormat> possibleFormatsList = new ArrayList<>();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        possibleFormatsList.add(format1);
        possibleFormatsList.add(format2);
        possibleFormatsList.add(format3);
        possibleFormatsList.add(format4);
        boolean correctFormat = false;
        Date date = null;
        int count = 0;
        for (SimpleDateFormat simpleDateFormat : possibleFormatsList) {
            boolean isValid = checkIfDateIsValid(simpleDateFormat.toPattern(), format);
            if (isValid) {
                try {
                    date = simpleDateFormat.parse(format);
                    if (date != null) {
                        this.calendar = Calendar.getInstance();
                        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        int year = localDateTime.getYear();
                        int month = localDateTime.getMonthValue();
                        int monthDay = localDateTime.getDayOfMonth();
                        int hours = localDateTime.getHour();
                        int minutes = localDateTime.getMinute();
                        int seconds = localDateTime.getSecond();
                        long milliseconds = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                        this.calendar.set(year, month, monthDay, hours, minutes, seconds);
                        this.calendar.setTimeInMillis(milliseconds);
                    }
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
//            if (format.matches(simpleDateFormat.toPattern())) {
//                try {
//                    date = simpleDateFormat.parse(format);
//                    if (date != null) {
//                        //this.calendar = Calendar.getInstance();
//                        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//                        int year = localDateTime.getYear();
//                        int month = localDateTime.getMonthValue();
//                        int monthDay = localDateTime.getDayOfMonth();
//                        int hours = localDateTime.getHour();
//                        int minutes = localDateTime.getMinute();
//                        int seconds = localDateTime.getSecond();
//                        long milliseconds = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//                        this.calendar.set(year, month, monthDay, hours, minutes, seconds);
//                        this.calendar.setTimeInMillis(milliseconds);
//                    }
//                    break;
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                count++;
//            }
        }
    }

    public test(long time) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(time - 62167399200000L);
//        int year = this.calendar.get(Calendar.YEAR);
//        this.calendar.set(Calendar.YEAR, year - 1970);
////        int day = this.calendar.get(Calendar.DAY_OF_MONTH);
////        this.calendar.set(Calendar.DAY_OF_MONTH, day - 1);
//        int hours = this.calendar.get(Calendar.HOUR);
//        this.calendar.set(Calendar.HOUR, hours - 3);
//        int leapYearQty = 1970 % 4;
//        long test1 = (long) (1970 - leapYearQty) * 365 * 24 * 60 * 60 * 1000;
//        long test2 = (long) leapYearQty * 365 * 24 * 60 * 60 * 1000;
//        long test = test1 + test2;
//        System.out.println("test: " + test);
        System.out.println("Calendar.YEAR: " + YEAR);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.calendar.getTime());
        System.out.println("getTime: " + date); //-62167399200000
        //System.out.println(this.calendar.YEAR + " " + this.calendar.MONTH + " " + this.calendar.DAY_OF_MONTH + " " + this.calendar.HOUR + " " + this.calendar.MINUTE + " " + this.calendar.SECOND + " " + this.calendar.MILLISECOND);
    }

    public static ua.com.alevel.CustomCalendar getInstance() {
        if (instance == null) {
            instance = new ua.com.alevel.CustomCalendar();
        }
        return instance;
    }

    private static boolean checkIfDateIsValid(String pattern, String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String now() {
//        this.calendar.setTimeInMillis(System.currentTimeMillis());
//        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//        Date dateNow = formatDate.parse(this.calendar.getTime());
//        return this.calendar.getTime();

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
    public long getTimeMillis() {
        if (this.calendar.get(YEAR) < 1970) {
            return this.calendar.getTimeInMillis() + 62167399200000L;
        } else {
            return this.calendar.getTimeInMillis();
        }
//        return this.calendar.getTimeInMillis() + 62167399200000L;
//        return this.calendar.getTimeInMillis();
//        return this.calendar.getTimeInMillis();
    }
    public void set(int year, int month) {
//        this.calendar.set(year, month);
        this.calendar.set(Calendar.YEAR, year);
        this.calendar.set(Calendar.MONTH, month);
    }
    public void set(int year, int month, int day, int hour, int minutes) {
        this.calendar.set(year, month, day, hour, minutes);
    }
    public void set(int year, int month, int day, int hour, int minutes, int seconds, int milliseconds) {
        this.calendar.set(year, month, day, hour, minutes, seconds);
        this.calendar.set(MILLISECOND, milliseconds);
    }
    public void set(String format) {
//        this.calendar.setTime();
    }
    public void addDate(ua.com.alevel.CustomCalendar date) {}
    public void addDate(String format) {
        ua.com.alevel.CustomCalendar cc = new ua.com.alevel.CustomCalendar(format);
    }
    public void addYears(int years) {
        this.calendar.add(YEAR, years);
    }
    public void addMonths(int months) {
        this.calendar.add(MONTH, months);
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
    public long minusDate(ua.com.alevel.CustomCalendar date) {
        long thisMilles = this.calendar.getTimeInMillis();
        long dateMillis = date.getTimeMillis();
        long diff = thisMilles - dateMillis;
        return diff;
    }
    public void minusDate(String format) {
        ua.com.alevel.CustomCalendar calForMinus = new ua.com.alevel.CustomCalendar(format);
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
    public static int differenceInYears(test first, test second) {
//        int year1 = first.calendar.get(YEAR);
//        int year2 = second.calendar.get(YEAR);
        return first.calendar.get(YEAR) - second.calendar.get(YEAR);
//        return (year1 - year2);
    }
    public static int differenceInMonths(test first, test second) {
        return first.calendar.get(MONTH) - second.calendar.get(MONTH);
    }
    public static int differenceInDays(test first, test second) {
//        long diff = first.calendar.getTimeInMillis() - second.calendar.getTimeInMillis();
////        return first.calendar.get(DAY_OF_YEAR) - second.calendar.get(DAY_OF_YEAR);
//        return (int) (diff / 1000 / 60 / 60 / 24);
        return differenceInHours(first, second) / 24;
    }
    public static int differenceInHours(test first, test second) {
        return (int) differenceInMinutes(first, second) / 60;
    }
    public static long differenceInMinutes(test first, test second) {
        return differenceInSeconds(first, second) / 60;
    }
    public static long differenceInSeconds(test first, test second) {
        return differenceInMilliseconds(first, second) / 1000;
    }
    public static long differenceInMilliseconds(test first, test second) {
        return first.calendar.getTimeInMillis() - second.calendar.getTimeInMillis();
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return dateFormat.format(this.calendar.getTime());
    }
}