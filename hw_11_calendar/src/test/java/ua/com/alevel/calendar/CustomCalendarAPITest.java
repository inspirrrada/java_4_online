package ua.com.alevel.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomCalendarAPITest {
    public CustomCalendar customCalendar;

    @Test
    public void checkEmptyConstructor() {
        customCalendar = new CustomCalendar();
        LocalDateTime localDateTime = LocalDateTime.now();

        Assertions.assertEquals(localDateTime.getYear(), customCalendar.getYear());
        Assertions.assertEquals(localDateTime.getMonthValue(), customCalendar.getMonth());
        Assertions.assertEquals(localDateTime.getDayOfMonth(), customCalendar.getDayInMonth());
        Assertions.assertEquals(localDateTime.getHour(), customCalendar.getHour());
        Assertions.assertEquals(localDateTime.getMinute(), customCalendar.getMinutes());
        Assertions.assertEquals(localDateTime.getSecond(), customCalendar.getSeconds());
    }

    @Test
    public void checkConstructorWithStringFormatDays() {
        customCalendar = new CustomCalendar("2022-10-09");

        Assertions.assertEquals(2022, customCalendar.getYear());
        Assertions.assertEquals(10, customCalendar.getMonth());
        Assertions.assertEquals(9, customCalendar.getDayInMonth());
        Assertions.assertEquals(0, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkConstructorWithStringFormatMinutes() {
        customCalendar = new CustomCalendar("1846-01-01 13:50");

        Assertions.assertEquals(1846, customCalendar.getYear());
        Assertions.assertEquals(1, customCalendar.getMonth());
        Assertions.assertEquals(1, customCalendar.getDayInMonth());
        Assertions.assertEquals(13, customCalendar.getHour());
        Assertions.assertEquals(50, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkConstructorWithStringFormatSeconds() {
        customCalendar = new CustomCalendar("0001-05-02 20:04:15");

        Assertions.assertEquals(1, customCalendar.getYear());
        Assertions.assertEquals(5, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(20, customCalendar.getHour());
        Assertions.assertEquals(4, customCalendar.getMinutes());
        Assertions.assertEquals(15, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkConstructorWithStringFormatMillis() {
        customCalendar = new CustomCalendar("1020-12-25 23:31:56 540");

        Assertions.assertEquals(1020, customCalendar.getYear());
        Assertions.assertEquals(12, customCalendar.getMonth());
        Assertions.assertEquals(25, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(31, customCalendar.getMinutes());
        Assertions.assertEquals(56, customCalendar.getSeconds());
        Assertions.assertEquals(540, customCalendar.getMilliseconds());
    }

    @Test
    public void checkConstructorWithMillis() {
        customCalendar = new CustomCalendar(1478728923999L);

        Assertions.assertEquals(46, customCalendar.getYear());
        Assertions.assertEquals(11, customCalendar.getMonth());
        Assertions.assertEquals(10, customCalendar.getDayInMonth());
        Assertions.assertEquals(22, customCalendar.getHour());
        Assertions.assertEquals(2, customCalendar.getMinutes());
        Assertions.assertEquals(3, customCalendar.getSeconds());
        Assertions.assertEquals(999, customCalendar.getMilliseconds());
    }

    @Test
    public void checkNow() {
        customCalendar = new CustomCalendar();
        LocalDateTime localDateTime = LocalDateTime.now();

        Assertions.assertEquals(localDateTime.getYear(), customCalendar.getYear());
        Assertions.assertEquals(localDateTime.getMonthValue(), customCalendar.getMonth());
        Assertions.assertEquals(localDateTime.getDayOfMonth(), customCalendar.getDayInMonth());
        Assertions.assertEquals(localDateTime.getHour(), customCalendar.getHour());
        Assertions.assertEquals(localDateTime.getMinute(), customCalendar.getMinutes());
        Assertions.assertEquals(localDateTime.getSecond(), customCalendar.getSeconds());
    }

    @Test
    public void checkGetTimeInMillis() {
        customCalendar = new CustomCalendar("0046-11-10 22:02:03 999");

        Assertions.assertEquals(1478728923999L, customCalendar.getTimeMillis());
    }

    @Test
    public void checkSetToMonths() {
        customCalendar = new CustomCalendar();
        customCalendar.set(1544, 10);

        Assertions.assertEquals(1544, customCalendar.getYear());
        Assertions.assertEquals(10, customCalendar.getMonth());
        Assertions.assertEquals(1, customCalendar.getDayInMonth());
        Assertions.assertEquals(0, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkSetToMinutes() {
        customCalendar = new CustomCalendar();
        customCalendar.set(2000, 7, 10, 5, 44);

        Assertions.assertEquals(2000, customCalendar.getYear());
        Assertions.assertEquals(7, customCalendar.getMonth());
        Assertions.assertEquals(10, customCalendar.getDayInMonth());
        Assertions.assertEquals(5, customCalendar.getHour());
        Assertions.assertEquals(44, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkSetToMillis() {
        customCalendar = new CustomCalendar();
        customCalendar.set(1078, 12, 31, 23, 55, 20, 845);

        Assertions.assertEquals(1078, customCalendar.getYear());
        Assertions.assertEquals(12, customCalendar.getMonth());
        Assertions.assertEquals(31, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(55, customCalendar.getMinutes());
        Assertions.assertEquals(20, customCalendar.getSeconds());
        Assertions.assertEquals(845, customCalendar.getMilliseconds());
    }

    @Test
    public void checkSetStringFormatMillis() {
        customCalendar = new CustomCalendar();
        customCalendar.set("1991-08-24 10:10:10 320");

        Assertions.assertEquals(1991, customCalendar.getYear());
        Assertions.assertEquals(8, customCalendar.getMonth());
        Assertions.assertEquals(24, customCalendar.getDayInMonth());
        Assertions.assertEquals(10, customCalendar.getHour());
        Assertions.assertEquals(10, customCalendar.getMinutes());
        Assertions.assertEquals(10, customCalendar.getSeconds());
        Assertions.assertEquals(320, customCalendar.getMilliseconds());
    }

    @Test
    public void checkSetStringFormatSeconds() {
        customCalendar = new CustomCalendar();
        customCalendar.set("1991-08-24 10:10:10");

        Assertions.assertEquals(1991, customCalendar.getYear());
        Assertions.assertEquals(8, customCalendar.getMonth());
        Assertions.assertEquals(24, customCalendar.getDayInMonth());
        Assertions.assertEquals(10, customCalendar.getHour());
        Assertions.assertEquals(10, customCalendar.getMinutes());
        Assertions.assertEquals(10, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkSetStringFormatMinutes() {
        customCalendar = new CustomCalendar();
        customCalendar.set("1991-08-24 10:10");

        Assertions.assertEquals(1991, customCalendar.getYear());
        Assertions.assertEquals(8, customCalendar.getMonth());
        Assertions.assertEquals(24, customCalendar.getDayInMonth());
        Assertions.assertEquals(10, customCalendar.getHour());
        Assertions.assertEquals(10, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkSetStringFormatDays() {
        customCalendar = new CustomCalendar();
        customCalendar.set("1991-08-24");

        Assertions.assertEquals(1991, customCalendar.getYear());
        Assertions.assertEquals(8, customCalendar.getMonth());
        Assertions.assertEquals(24, customCalendar.getDayInMonth());
        Assertions.assertEquals(0, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddDateWithCalendarArgument() {
        customCalendar = new CustomCalendar("2020-05-06 13:10:00");
        CustomCalendar customCalendar2 = new CustomCalendar("0005-05-05 01:10:00");
        customCalendar.addDate(customCalendar2);

        Assertions.assertEquals(2025, customCalendar.getYear());
        Assertions.assertEquals(10, customCalendar.getMonth());
        Assertions.assertEquals(11, customCalendar.getDayInMonth());
        Assertions.assertEquals(14, customCalendar.getHour());
        Assertions.assertEquals(20, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddDateWithFormatArgument() {
        customCalendar = new CustomCalendar("1011-05-06 13:10:00");
        customCalendar.addDate("0005-08-05 01:10:00");

        Assertions.assertEquals(1017, customCalendar.getYear());
        Assertions.assertEquals(1, customCalendar.getMonth());
        Assertions.assertEquals(11, customCalendar.getDayInMonth());
        Assertions.assertEquals(14, customCalendar.getHour());
        Assertions.assertEquals(20, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddMonths() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.addMonths(12);

        Assertions.assertEquals(1001, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddDays() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.addDays(18);
        ;

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(20, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddHours() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.addHours(2);
        ;

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(3, customCalendar.getDayInMonth());
        Assertions.assertEquals(1, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddMinutes() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.addMinutes(33);
        ;

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(33, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddSeconds() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.addSeconds(45);
        ;

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(50, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkAddMilliseconds() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.addMilliseconds(20);

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(30, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusDateWithCalendarArgument() {
        customCalendar = new CustomCalendar("2020-05-06 13:10:00");
        CustomCalendar customCalendar2 = new CustomCalendar("0005-04-05 01:10:00");
        customCalendar.minusDate(customCalendar2);

        Assertions.assertEquals(2015, customCalendar.getYear());
        Assertions.assertEquals(1, customCalendar.getMonth());
        Assertions.assertEquals(1, customCalendar.getDayInMonth());
        Assertions.assertEquals(12, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusDateWithFormatArgument() {
        customCalendar = new CustomCalendar("1010-05-06 13:10:00");
        customCalendar.minusDate("0005-03-05 01:10:00");

        Assertions.assertEquals(1005, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(1, customCalendar.getDayInMonth());
        Assertions.assertEquals(12, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(0, customCalendar.getSeconds());
        Assertions.assertEquals(0, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusMonths() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.minusMonths(3);

        Assertions.assertEquals(999, customCalendar.getYear());
        Assertions.assertEquals(11, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusDays() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.minusDays(18);

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(1, customCalendar.getMonth());
        Assertions.assertEquals(15, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusHours() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.minusHours(2);

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(21, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusMinutes() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.minusMinutes(35);

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(22, customCalendar.getHour());
        Assertions.assertEquals(25, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusSeconds() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.minusSeconds(10);
        ;

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(22, customCalendar.getHour());
        Assertions.assertEquals(59, customCalendar.getMinutes());
        Assertions.assertEquals(55, customCalendar.getSeconds());
        Assertions.assertEquals(10, customCalendar.getMilliseconds());
    }

    @Test
    public void checkMinusMilliseconds() {
        customCalendar = new CustomCalendar("1000-02-02 23:00:05 010");
        customCalendar.minusMilliseconds(6);

        Assertions.assertEquals(1000, customCalendar.getYear());
        Assertions.assertEquals(2, customCalendar.getMonth());
        Assertions.assertEquals(2, customCalendar.getDayInMonth());
        Assertions.assertEquals(23, customCalendar.getHour());
        Assertions.assertEquals(0, customCalendar.getMinutes());
        Assertions.assertEquals(5, customCalendar.getSeconds());
        Assertions.assertEquals(4, customCalendar.getMilliseconds());
    }

    @Test
    public void checkDifferenceInYearsWhenFirstDateMoreThanSecond() {
        customCalendar = new CustomCalendar("2023-02-02 23:00:05 010");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-12-05 04:45:53 999");
        int diff = CustomCalendar.differenceInYears(customCalendar, customCalendar2);

        Assertions.assertEquals(3, diff);
    }

    @Test
    public void checkDifferenceInYearsWhenFirstDateLessThanSecond() {
        customCalendar = new CustomCalendar("2020-05-05 04:45:53 999");
        CustomCalendar customCalendar2 = new CustomCalendar("2023-05-02 23:00:05 010");
        int diff = CustomCalendar.differenceInYears(customCalendar, customCalendar2);

        Assertions.assertEquals(3, diff);
    }

    @Test
    public void checkDifferenceInMonthsWhenFirstDateMoreThanSecond() {
        customCalendar = new CustomCalendar("2023-05-02 23:00:05 010");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-05-05 04:45:53 999");
        int diff = CustomCalendar.differenceInMonths(customCalendar, customCalendar2);

        Assertions.assertEquals(36, diff);
    }

    @Test
    public void checkDifferenceInMonthsWhenFirstDateLessThanSecond() {
        customCalendar = new CustomCalendar("2020-05-05 04:45:53 999");
        CustomCalendar customCalendar2 = new CustomCalendar("2023-05-02 23:00:05 010");
        int diff = CustomCalendar.differenceInMonths(customCalendar, customCalendar2);

        Assertions.assertEquals(36, diff);
    }

    @Test
    public void checkDifferenceInDaysWhenFirstDateMoreThanSecond() {
        customCalendar = new CustomCalendar("2023-05-02 23:00:05 010");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-05-02 04:45:53 999");
        int diff = CustomCalendar.differenceInDays(customCalendar, customCalendar2);

        Assertions.assertEquals(1097, diff);
    }

    @Test
    public void checkDifferenceInDaysWhenFirstDateLessThanSecond() {
        customCalendar = new CustomCalendar("2020-05-05 04:45:53 999");
        CustomCalendar customCalendar2 = new CustomCalendar("2023-05-02 23:00:05 010");
        int diff = CustomCalendar.differenceInDays(customCalendar, customCalendar2);

        Assertions.assertEquals(1100, diff);
    }

    @Test
    public void checkDifferenceInDaysWithinOneYear() {
        customCalendar = new CustomCalendar("2020-05-05 04:45:53 999");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-05-02 23:00:05 010");
        int diff = CustomCalendar.differenceInDays(customCalendar, customCalendar2);

        Assertions.assertEquals(3, diff);
    }

    @Test
    public void checkDifferenceInDaysWithinTwoYears() {
        customCalendar = new CustomCalendar("2018-10-10");
        CustomCalendar customCalendar2 = new CustomCalendar("2019-10-10");
        int diff = CustomCalendar.differenceInDays(customCalendar, customCalendar2);

        Assertions.assertEquals(365, diff);
    }

    @Test
    public void checkDifferenceInHours() {
        customCalendar = new CustomCalendar("2020-05-05 04:45:53 999");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-05-05 04:00:05 010");
        long diff = CustomCalendar.differenceInHours(customCalendar, customCalendar2);

        Assertions.assertEquals(0, diff);
    }

    @Test
    public void checkDifferenceInMinutes() {
        customCalendar = new CustomCalendar("2020-05-05 21:00");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-05-05 23:00");
        long diff = CustomCalendar.differenceInMinutes(customCalendar, customCalendar2);

        Assertions.assertEquals(120, diff);
    }

    @Test
    public void checkDifferenceInSeconds() {
        customCalendar = new CustomCalendar("2020-05-05 22:55:00 010");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-05-05 23:00:05 010");
        long diff = CustomCalendar.differenceInSeconds(customCalendar, customCalendar2);

        Assertions.assertEquals(305, diff);
    }

    @Test
    public void checkDifferenceInMillis() {
        customCalendar = new CustomCalendar("2020-05-05 23:00:04 000");
        CustomCalendar customCalendar2 = new CustomCalendar("2020-05-05 23:00:05 010");
        long diff = CustomCalendar.differenceInMilliseconds(customCalendar, customCalendar2);

        Assertions.assertEquals(1010, diff);
    }
}
