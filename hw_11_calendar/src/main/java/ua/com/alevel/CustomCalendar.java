package ua.com.alevel;

import ua.com.alevel.utils.TimeConstants;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;
import static java.util.Calendar.MONTH;

public class CustomCalendar {
    private long dateInMillis;
    private int year;
    private int month;
    private int dayInYear;
    private int dayInMonth;
    private int hour;
    private int minutes;
    private int seconds;
    private int milliseconds;
    private static final String FORMAT_DAYS = "yyyy-MM-dd";
    private static final String FORMAT_MINUTES = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss SSS";
    private static final String[] POSSIBLE_FORMATS = new String[]{FORMAT_DAYS, FORMAT_MINUTES, FORMAT_SECONDS, FORMAT_MILLIS};

    public CustomCalendar() {
        long millis = new Date().getTime();
        MonthDay md;
    }
    public CustomCalendar(String format) {
        String[] formatArray = format.split(" ");
        String[] dateArray = formatArray[0].split("-");
        this.year = Integer.parseInt(dateArray[0]);
        this.month = Integer.parseInt(dateArray[1]);
        this.dayInMonth = Integer.parseInt(dateArray[2]);
        if (formatArray.length > 1 ) {
            String[] timeArray = formatArray[1].split(":");
            this.hour = Integer.parseInt(timeArray[0]);
            this.minutes = Integer.parseInt(timeArray[1]);
            if (timeArray.length > 2) {
                this.seconds = Integer.parseInt(timeArray[2]);
            }
        }
        if (formatArray.length > 2) {
            this.milliseconds = Integer.parseInt(formatArray[2]);
        }
        System.out.println(this.year + " year, " + this.dayInYear + " day in year, " + this.month + " month, " + this.dayInMonth + " day, " + this.hour + " hour, " + this.minutes + " minutes, " + this.seconds + " seconds, " + this.milliseconds + " milliseconds.");

    }
    public CustomCalendar(long time) {
        this.year = getYears(time);
        long millisInYears = getMillisInYear(this.year);
        long leftMillisL = time - millisInYears;
        this.dayInYear = getDays(leftMillisL);
        getAndSetMonthAndDay(this.dayInYear, this.year);
        int leftMillis = (int) (leftMillisL - dayInYear * TimeConstants.MILLIS_IN_ONE_DAY);
        this.hour = getHours(leftMillis);
        leftMillis = leftMillis - this.hour * TimeConstants.MILLIS_IN_ONE_HOUR;
        this.minutes = getMinutes(leftMillis);
        leftMillis = leftMillis - this.minutes * TimeConstants.MILLIS_IN_ONE_MINUTE;
        this.seconds = getSeconds(leftMillis);
        this.milliseconds = leftMillis - this.seconds * TimeConstants.MILLIS_IN_ONE_SECOND;
        System.out.println(this.year + " year, " + this.dayInYear + " day in year, " + this.month + " month, " + this.dayInMonth + " day, " + this.hour + " hour, " + this.minutes + " minutes, " + this.seconds + " seconds, " + this.milliseconds + " milliseconds.");
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public long getTimeMillis() {
       int leapYears = getLeapYearsQty(this.year);
       int nonLeapYears = this.year - leapYears;
       long yearsMillis = (leapYears * TimeConstants.MILLIS_IN_ONE_LEAP_YEAR + nonLeapYears * TimeConstants.MILLIS_IN_ONE_NON_LEAP_YEAR);
        long daysMillis = (long) getDayInYear(this.month, this.dayInMonth) * TimeConstants.MILLIS_IN_ONE_DAY;
        long hoursMillis =  (long) this.hour * TimeConstants.MILLIS_IN_ONE_HOUR;
        long minutesMillis = (long) this.minutes * TimeConstants.MILLIS_IN_ONE_MINUTE;
        long secondMillis =  (long) this.seconds * TimeConstants.MILLIS_IN_ONE_SECOND;
        long millis = this.milliseconds;
       long timeMillis = (leapYears * TimeConstants.MILLIS_IN_ONE_LEAP_YEAR + nonLeapYears * TimeConstants.MILLIS_IN_ONE_NON_LEAP_YEAR) +
               (long) getDayInYear(this.month, this.dayInMonth) * TimeConstants.MILLIS_IN_ONE_DAY +
               (long) this.hour * TimeConstants.MILLIS_IN_ONE_HOUR +
               (long) this.minutes * TimeConstants.MILLIS_IN_ONE_MINUTE +
               (long) this.seconds * TimeConstants.MILLIS_IN_ONE_SECOND +
               this.milliseconds;
        System.out.println(timeMillis + "ms");
       return timeMillis;
    }

    /*public void set(int year, int month) {
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
*/

    /**
     * additional methods
     */

    private int getLeapYearsQty(int years) {
        return years / 4;
    }

    private int getYears(long millis) {
        return (int) (millis / TimeConstants.AVERAGE_MILLIS_IN_ONE_YEAR);
    }

    private int getDays(long millis) {
        return (int) (millis / TimeConstants.MILLIS_IN_ONE_DAY);
    }

    private int getHours(long millis) {
        return (int) (millis / TimeConstants.MILLIS_IN_ONE_HOUR);
    }

    private int getMinutes(long millis) {
        return (int) (millis / TimeConstants.MILLIS_IN_ONE_MINUTE);
    }

    private int getSeconds(long millis) {
        return (int) (millis / TimeConstants.MILLIS_IN_ONE_SECOND);
    }

    private long getMillisInYear(int year) {
        int leapYearsQty = getLeapYearsQty(year);
        int nonLeapYearsQty = year - leapYearsQty;
       return (leapYearsQty * TimeConstants.MILLIS_IN_ONE_LEAP_YEAR) + (nonLeapYearsQty * TimeConstants.MILLIS_IN_ONE_NON_LEAP_YEAR);
    }

    private void getAndSetMonthAndDay(int dayInYear, int year) {
        int days = 0;
        boolean isYearLeap = (year % 4) == 0;
        for (int i = 0; i < TimeConstants.DAYS_IN_MONTHS.length; i++) {
            days += TimeConstants.DAYS_IN_MONTHS[i];
            if (dayInYear != 0) {
                if ((days / dayInYear) == 0) {
                    if (i == 1 && isYearLeap) {
                        days++;
                    }
                } else {
                    this.month = i + 1;
                    this.dayInMonth = TimeConstants.DAYS_IN_MONTHS[i] - (days - dayInYear) + 1;
                    break;
                }
            } else {
                this.month = 0;
                this.dayInMonth = 1;
            }
        }
    }

    private int getDayInYear(int month, int dayInMonth) {
        int days = 0;
        boolean isYearLeap = (year % 4) == 0;
        for (int i = 0; i < month-1; i++) {
            days += TimeConstants.DAYS_IN_MONTHS[i];
            if (i == 1 && isYearLeap) {
                days++;
            }
        }
        return days + dayInMonth -1;
    }



}
