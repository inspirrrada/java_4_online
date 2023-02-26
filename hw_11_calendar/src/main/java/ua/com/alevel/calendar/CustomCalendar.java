package ua.com.alevel.calendar;

import ua.com.alevel.utils.TimeConstants;
import java.time.Month;
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
        this.dayInMonth = 1;
        this.hour = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.milliseconds = 0;
    }

    public void set(int year, int month, int day, int hour, int minutes) {
        this.set(year, month);
        this.dayInMonth = day;
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = 0;
        this.milliseconds = 0;
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
        if (formatArray.length > 1) {
            String[] timeArray = formatArray[1].split(":");
            this.hour = Integer.parseInt(timeArray[0]);
            this.minutes = Integer.parseInt(timeArray[1]);
            if (timeArray.length > 2) {
                this.seconds = Integer.parseInt(timeArray[2]);
            } else {
                this.seconds = 0;
            }
        } else {
            this.hour = 0;
            this.minutes = 0;
            this.seconds = 0;
            this.milliseconds = 0;
        }
        if (formatArray.length > 2) {
            this.milliseconds = Integer.parseInt(formatArray[2]);
        } else {
            this.milliseconds = 0;
        }
    }

    public void addDate(CustomCalendar date) {
        int milliseconds = this.milliseconds + date.milliseconds;
        if (milliseconds >= 1000) {
            this.milliseconds = milliseconds - 1000;
            this.seconds++;
        } else {
            this.milliseconds = milliseconds;
        }
        int seconds = this.seconds + date.seconds;
        if (seconds >= 60) {
            this.seconds = seconds - 60;
            this.minutes++;
        } else {
            this.seconds = seconds;
        }
        int minutes = this.minutes + date.minutes;
        if (minutes >= 60) {
            this.minutes = minutes - 60;
            this.hour++;
        } else {
            this.minutes = minutes;
        }
        int hours = this.hour + date.hour;
        if (hours >= 24) {
            this.hour = hours - 24;
            this.dayInMonth++;
            this.dayInYear++;
        } else {
            this.hour = hours;
        }
        int months = this.month + date.month;
        if (months > 12) {
            this.month = months - 12;
            this.year++;
        } else {
            this.month = months;
        }
        int days = this.dayInMonth + date.dayInMonth;
        int monthDaysQty = TimeConstants.DAYS_IN_MONTHS[this.month - 1];
        if (days > monthDaysQty) {
            this.dayInMonth = days - monthDaysQty;
            this.month++;
        } else {
            this.dayInMonth = days;
        }
        this.year = this.year + date.year;
    }

    public void addDate(String format) {
        CustomCalendar calendar = new CustomCalendar(format);
        int milliseconds = this.milliseconds + calendar.milliseconds;
        if (milliseconds >= 1000) {
            this.milliseconds = milliseconds - 1000;
            this.seconds++;
        } else {
            this.milliseconds = milliseconds;
        }
        int seconds = this.seconds + calendar.seconds;
        if (seconds >= 60) {
            this.seconds = seconds - 60;
            this.minutes++;
        } else {
            this.seconds = seconds;
        }
        int minutes = this.minutes + calendar.minutes;
        if (minutes >= 60) {
            this.minutes = minutes - 60;
            this.hour++;
        } else {
            this.minutes = minutes;
        }
        int hours = this.hour + calendar.hour;
        if (hours >= 24) {
            this.hour = hours - 24;
            this.dayInMonth++;
            this.dayInYear++;
        } else {
            this.hour = hours;
        }
        int months = this.month + calendar.month;
        if (months > 12) {
            this.month = months - 12;
            this.year++;
        } else {
            this.month = months;
        }
        int days = this.dayInMonth + calendar.dayInMonth;
        int monthDaysQty = TimeConstants.DAYS_IN_MONTHS[this.month - 1];
        if (days > monthDaysQty) {
            this.dayInMonth = days - monthDaysQty;
            this.month++;
        } else {
            this.dayInMonth = days;
        }
        this.year = this.year + calendar.year;
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
        int milliseconds = this.milliseconds - date.milliseconds;
        if (milliseconds < 0) {
            this.milliseconds = milliseconds + 1000;
            this.seconds--;
        } else {
            this.milliseconds = milliseconds;
        }
        int seconds = this.seconds - date.seconds;
        if (seconds < 0) {
            this.seconds = seconds + 60;
            this.minutes--;
        } else {
            this.seconds = seconds;
        }
        int minutes = this.minutes - date.minutes;
        if (minutes < 0) {
            this.minutes = minutes + 60;
            this.hour--;
        } else {
            this.minutes = minutes;
        }
        int hours = this.hour - date.hour;
        if (hours < 0) {
            this.hour = hours + 24;
            this.dayInMonth--;
            this.dayInYear--;
        } else {
            this.hour = hours;
        }
        int months = this.month - date.month;
        if (months <= 0) {
            this.month = months + 12;
            this.year--;
        } else {
            this.month = months;
        }
        int days = this.dayInMonth - date.dayInMonth;
        if (days < 0) {
            this.dayInMonth = days * -1;
            this.month--;
        } else {
            this.dayInMonth = days;
        }
        this.year = this.year - date.year;
    }

    public void minusDate(String format) {
        CustomCalendar calendar = new CustomCalendar(format);
        int milliseconds = this.milliseconds - calendar.milliseconds;
        if (milliseconds < 0) {
            this.milliseconds = milliseconds + 1000;
            this.seconds--;
        } else {
            this.milliseconds = milliseconds;
        }
        int seconds = this.seconds - calendar.seconds;
        if (seconds < 0) {
            this.seconds = seconds + 60;
            this.minutes--;
        } else {
            this.seconds = seconds;
        }
        int minutes = this.minutes - calendar.minutes;
        if (minutes < 0) {
            this.minutes = minutes + 60;
            this.hour--;
        } else {
            this.minutes = minutes;
        }
        int hours = this.hour - calendar.hour;
        if (hours < 0) {
            this.hour = hours + 24;
            this.dayInMonth--;
            this.dayInYear--;
        } else {
            this.hour = hours;
        }
        int months = this.month - calendar.month;
        if (months <= 0) {
            this.month = months + 12;
            this.year--;
        } else {
            this.month = months;
        }
        int days = this.dayInMonth - calendar.dayInMonth;
        if (days < 0) {
            this.dayInMonth = days * -1;
            this.month--;
        } else {
            this.dayInMonth = days;
        }
        this.year = this.year - calendar.year;
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
        long total = thisMillis - milliseconds;
        this.transformMillisToCalendarDate(total);
    }

    public static int differenceInYears(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.year - second.year);
    }

    public static int differenceInMonths(CustomCalendar first, CustomCalendar second) {
        int diffInYears = differenceInYears(first, second);
        ++diffInYears;  //include current year
        int diffInMonths = (diffInYears * 12) - (first.month + 1) - (second.month + 1); // +1 for include current months in calculation
        return Math.abs(diffInMonths);
    }

    public static int differenceInDays(CustomCalendar first, CustomCalendar second) {
        int diffInYears = differenceInYears(first, second);
        int leapYears = CustomCalendar.getLeapYearsQty(first.year, second.year);
        int daysInFullYears = leapYears * 366 + (diffInYears - leapYears) * 365;
        int diffInDays;
        if (first.getTimeMillis() >= second.getTimeMillis()) {
            diffInDays = daysInFullYears - first.getDayInYear(first.month, first.dayInMonth) + second.getDayInYear(second.month, second.dayInMonth);
        } else {
            diffInDays = daysInFullYears - second.getDayInYear(second.month, second.dayInMonth) + first.getDayInYear(first.month, first.dayInMonth);
        }
        if (first.year == second.year) {
            diffInDays -= 1;
        }
        return Math.abs(diffInDays);
    }

    public static long differenceInHours(CustomCalendar first, CustomCalendar second) {
        return differenceInMinutes(first, second) / 60;
    }

    public static long differenceInMinutes(CustomCalendar first, CustomCalendar second) {
        return differenceInSeconds(first, second) / 60;
    }

    public static long differenceInSeconds(CustomCalendar first, CustomCalendar second) {
        return differenceInMilliseconds(first, second) / 1000;
    }

    public static long differenceInMilliseconds(CustomCalendar first, CustomCalendar second) {
        return Math.abs(first.getTimeMillis() - second.getTimeMillis());
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

    private static int getLeapYearsQty(int firstYear, int secondYear) {
        int leapYear = 0;
        if (firstYear <= secondYear) {
            for (int i = firstYear; i <= secondYear; i++) {
                if (i % 4 == 0) {
                    leapYear++;
                }
            }
        } else {
            for (int i = secondYear; i <= firstYear; i++) {
                if (i % 4 == 0) {
                    leapYear++;
                }
            }
        }

        return leapYear;
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
        for (int i = 0; i < month - 1; i++) {
            days += TimeConstants.DAYS_IN_MONTHS[i];
            if (i == 1 && isYearLeap) {
                days++;
            }
        }
        return days + dayInMonth - 1;
    }

    @Override
    public String toString() {
        return this.dayInMonth + " " +
                Month.of(this.month) + " of " +
                this.year + " year and " +
                this.hour + " hours, " +
                this.minutes + " minutes, " +
                this.seconds + " seconds, " +
                this.milliseconds + " milliseconds.";
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayInMonth() {
        return dayInMonth;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }
}
