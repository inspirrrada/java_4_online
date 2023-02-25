package ua.com.alevel.calendar;

import ua.com.alevel.utils.CustomCalendarFormats;
import ua.com.alevel.utils.TimeConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class CustomCalendar {
    private int year;
    private int month;
    private int dayInYear;
    private int dayInMonth;
    private int hour;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public CustomCalendar() {
        this(new Date().getTime() + 62168436000000L);
    }
    public CustomCalendar(String format) {
        this.set(format);
    }
    public CustomCalendar(long time) {
       this.transformMillisToCalendarDate(time);
    }

    public static CustomCalendar now() {
        return new CustomCalendar();
    }

    public long getTimeMillis() {
       int leapYears = getLeapYearsQty(this.year);
       int nonLeapYears = this.year - leapYears;
       long timeMillis = (leapYears * TimeConstants.MILLIS_IN_ONE_LEAP_YEAR + nonLeapYears * TimeConstants.MILLIS_IN_ONE_NON_LEAP_YEAR) +
               (long) getDayInYear(this.month, this.dayInMonth) * TimeConstants.MILLIS_IN_ONE_DAY +
               (long) this.hour * TimeConstants.MILLIS_IN_ONE_HOUR +
               (long) this.minutes * TimeConstants.MILLIS_IN_ONE_MINUTE +
               (long) this.seconds * TimeConstants.MILLIS_IN_ONE_SECOND +
               this.milliseconds;
       return timeMillis;
    }

    public void set(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public void set(int year, int month, int day, int hour, int minutes) {
        this.set(year, month);
        this.dayInMonth = day;
        this.hour = hour;
        this.minutes = minutes;
    }

    public void set(int year, int month, int day, int hour, int minutes, int seconds, int milliseconds) {
        this.set(year, month, day, hour, minutes);
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public void set(String format) {
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
    }

    public void addDate(CustomCalendar date) {
        long total = this.getTimeMillis() + date.getTimeMillis();
        this.transformMillisToCalendarDate(total);
    }

    public void addDate(String format) {
        CustomCalendar calendar = new CustomCalendar(format);
        this.addDate(calendar);
    }

    public void addYears(int years) {
        this.year += years;
    }

    public void addMonths(int months) {
        if ((this.month + months) > 12) {
            this.year += 1;
            this.month = this.month + months - 12;
        } else {
            this.month = this.month + months;
        }
    }

    public void addDays(int days) {
        long thisMillis = this.getTimeMillis();
        long daysMillis = (long) days * TimeConstants.MILLIS_IN_ONE_DAY;
        long total = thisMillis + daysMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void addHours(int hours) {
        long thisMillis = this.getTimeMillis();
        int hoursMillis = hours * TimeConstants.MILLIS_IN_ONE_HOUR;
        long total = thisMillis + hoursMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void addMinutes(int minutes) {
        long thisMillis = this.getTimeMillis();
        int minutesMillis = minutes * TimeConstants.MILLIS_IN_ONE_MINUTE;
        long total = thisMillis + minutesMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void addSeconds(int seconds) {
        long thisMillis = this.getTimeMillis();
        int secondMillis = seconds * TimeConstants.MILLIS_IN_ONE_SECOND;
        long total = thisMillis + secondMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void addMilliseconds(int milliseconds) {
        long thisMillis = this.getTimeMillis();
        long total = thisMillis + milliseconds;
        this.transformMillisToCalendarDate(total);
    }

    public void minusDate(CustomCalendar date) {
        long total = this.getTimeMillis() - date.getTimeMillis();
        this.transformMillisToCalendarDate(total);
    }

    public void minusDate(String format) {
        CustomCalendar calendar = new CustomCalendar(format);
        this.minusDate(calendar);
    }

    public void minusYears(int years) {
        this.year -= years;
    }

    public void minusMonths(int months) {
        if ((this.month - months) <= 0) {
            this.year -= 1;
            this.month = this.month - months + 12;
        } else {
            this.month = this.month - months;
        }
    }

    public void minusDays(int days) {
        long thisMillis = this.getTimeMillis();
        long daysMillis = (long) days * TimeConstants.MILLIS_IN_ONE_DAY;
        long total = thisMillis - daysMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void minusHours(int hours) {
        long thisMillis = this.getTimeMillis();
        int hoursMillis = hours * TimeConstants.MILLIS_IN_ONE_HOUR;
        long total = thisMillis - hoursMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void minusMinutes(int minutes) {
        long thisMillis = this.getTimeMillis();
        int minutesMillis = minutes * TimeConstants.MILLIS_IN_ONE_MINUTE;
        long total = thisMillis - minutesMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void minusSeconds(int seconds) {
        long thisMillis = this.getTimeMillis();
        int secondMillis = seconds * TimeConstants.MILLIS_IN_ONE_SECOND;
        long total = thisMillis - secondMillis;
        this.transformMillisToCalendarDate(total);
    }

    public void minusMilliseconds(int milliseconds) {
        long thisMillis = this.getTimeMillis();
        long total = thisMillis + milliseconds;
        this.transformMillisToCalendarDate(total);
    }

    public static int differenceInYears(CustomCalendar first, CustomCalendar second) {
        return first.year- second.year;
    }

    public static int differenceInMonths(CustomCalendar first, CustomCalendar second) {
        return first.year- second.year;
    }

    public static int differenceInDays(CustomCalendar first, CustomCalendar second) {
        return first.dayInYear- second.dayInYear;
    }

    public static int differenceInHours(CustomCalendar first, CustomCalendar second) {
        return first.hour- second.hour;
    }

    public static long differenceInMinutes(CustomCalendar first, CustomCalendar second) {
        return first.minutes- second.minutes;
    }

    public static long differenceInSeconds(CustomCalendar first, CustomCalendar second) {
        return first.seconds- second.seconds;
    }

    public static long differenceInMilliseconds(CustomCalendar first, CustomCalendar second) {
        return first.getTimeMillis() - second.getTimeMillis();
    }

    /**
     * additional methods
     */

    private void transformMillisToCalendarDate(long time) {
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
    }

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
                if ((days / dayInYear) == 0 ) {
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

    @Override
    public String toString() {
        return "CustomCalendar {" +
                " year = " + this.year +
                ", month = " + this.month +
                ", dayInMonth = " + this.dayInMonth +
                ", hour = " + this.hour +
                ", minutes = " + this.minutes +
                ", seconds = " + this.seconds +
                ", milliseconds = " + this.milliseconds + " " +
                '}';
    }
}
