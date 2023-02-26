package ua.com.alevel.utils;

public class TimeConstants {
    public static final int MILLIS_IN_ONE_SECOND = 1000;
    public static final int MILLIS_IN_ONE_MINUTE = MILLIS_IN_ONE_SECOND * 60;
    public static final int MILLIS_IN_ONE_HOUR = MILLIS_IN_ONE_MINUTE * 60;
    public static final int MILLIS_IN_ONE_DAY = MILLIS_IN_ONE_HOUR * 24;
    public static final long MILLIS_IN_ONE_NON_LEAP_YEAR = MILLIS_IN_ONE_DAY * 365L;
    public static final long MILLIS_IN_ONE_LEAP_YEAR = MILLIS_IN_ONE_DAY * 366L;
    public static final long AVERAGE_MILLIS_IN_ONE_YEAR = (long) (MILLIS_IN_ONE_DAY * 365.25 * 1L);
    public static final int[] DAYS_IN_MONTHS = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
}
