package ua.com.alevel;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        CustomCalendar c = new CustomCalendar(31536000000L);
        CustomCalendar c = new CustomCalendar(1478728923999L);
        System.out.println(c.getTimeMillis());
        //System.out.println(new Date(Long.MAX_VALUE));
//        new CustomCalendarController().start();
//        System.out.println(new CustomCalendar(1577829600000L));
//        String myDate1 = "2016-11-11";
//        String myDate2 = "0000-01-00";
//        String myDate3 = "0000-00-00";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date test1 = sdf.parse(myDate1);
//            Date test2 = sdf.parse(myDate2);
//            Date test3 = sdf.parse(myDate3);
//            System.out.println("2016-11-11, millis: " + test1.getTime());
//            System.out.println("0000-01-00, millis: " + test2.getTime());
//            System.out.println("0000-00-00, millis: " + test3.getTime());
//            System.out.println("date of 1577829600000: " + new Date(1577829600000L));
//            System.out.println("date of 1602277200000L: " + new Date(1602277200000L));
//            System.out.println("date of 3180106800000L: " + new Date(3180106800000L));
//            Duration d = Duration.ofMinutes(10);
//            CustomCalendar cc1 = new CustomCalendar(test1.getTime());
//            CustomCalendar cc2 = new CustomCalendar(test2.getTime());
//            CustomCalendar cc3 = new CustomCalendar(test3.getTime());
//            Calendar cal1 = Calendar.getInstance();
//            Calendar cal2 = Calendar.getInstance();
//            Calendar cal3 = Calendar.getInstance();
//            cal1.setTime(test1);
//            cal2.setTime(test2);
//            cal3.setTime(test3);
//            System.out.println("cc1: " + cc1);
//            System.out.println("cc2: " + cc2);
//            System.out.println("cc3: " + cc3);
//            System.out.println("cal1: " + cal1.YEAR + " " + cal1.MONTH + " " + cal1.DATE);
//            System.out.println("cal2: " + cal2.YEAR + " " + cal2.MONTH + " " + cal2.DATE);
//            System.out.println("cal3: " + cal3.YEAR + " " + cal3.MONTH + " " + cal3.DATE);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(new CustomCalendar(0L));
//        System.out.println("Hello world from hw_11!");
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getTime());
//        System.out.println(Instant.ofEpochMilli(calendar.getTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
//
//        Date date = new Date(41479200000L);
//        System.out.println("date: " + date);
////Set time in milliseconds
//        c.setTimeInMillis(0);
//        int mYear = c.get(Calendar.YEAR);
//        int mMonth = c.get(Calendar.MONTH);
//        int mDay = c.get(Calendar.DAY_OF_MONTH);
//        int hr = c.get(Calendar.HOUR);
//        int min = c.get(Calendar.MINUTE);
//        int sec = c.get(Calendar.SECOND);
//        int millis = c.get(Calen
//        4r edar.MILLISECOND);
//        System.out.println("time: " + mYear + " " + mMonth + " " + mDay + " " + hr + " " + min + " " + sec + " " + millis);
//        System.out.println(ZonedDateTime.now().getOffset().getTotalSeconds() / 360);
//        System.out.println(new CustomCalendar());
//        System.out.println(new CustomCalendar("2023-02-02"));
//        new CustomCalendar("2023-02-02-05");
//        System.out.println(new CustomCalendar("2023-02-02 22:09"));
//        System.out.println(new CustomCalendar("2023-02-02 22:09:59"));
//        System.out.println(new CustomCalendar("2023-02-02 22:09:59 999"));
//        System.out.println(new test("2023-02-02"));
////        new CustomCalendar("2023-02-02-05");
//        System.out.println(new test("2023-02-02 22:09"));
//        System.out.println(new test("2023-02-02 22:09:59"));
//        System.out.println(new test("2023-02-02 22:09:59 999"));
//        System.out.println(new CustomCalendar(41479200000L));
//        System.out.println(new CustomCalendar(41479200000L).getTimeMillis());
//        System.out.println(new CustomCalendar(0L).getTimeMillis());
//        System.out.println(c.set(Calendar.MILLISECOND, 41479200000L));
//        CustomCalendar cc2 = new CustomCalendar(0L);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(0L);
//        System.out.println("cc2.getTimeMillis():" + cc2.getTimeMillis());
//        CustomCalendar cc = new CustomCalendar("0000-01-00 00:00:00"); //63843948058863
//        cc.getTimeMillis();
//        System.out.println("cc.getTimeMillis(): " + cc.getTimeMillis());
//        Calendar c3 = Calendar.getInstance();
//        c3.setTimeInMillis(41479200000L);
//        System.out.println("c3.getTime(): " + c3.getTime());
//        Calendar c4 = Calendar.getInstance();
//        Date d = new Date(41479200000L);
//        c4.setTime(d);
//        System.out.println("c4.getTimeInMillis(): " + c4.getTimeInMillis());
//        c4.setTimeInMillis(31536000000L);
//        System.out.println(c4.getTime());

//        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK);
//        String pattern = "yyyy-MM-dd HH:mm";
//        String date = "2023-02-02 22:09";
//        System.out.println(date.matches(pattern));
//        CustomCalendar cc1 = new CustomCalendar("2019-06-02-80");
//        cc1.set("2020-08-16-08");
//        System.out.println(cc1);
//        System.out.println("cc1.now(): " + cc1.now());
//        CustomCalendar cc2 = new CustomCalendar("2020-02-02");
//        int diff = differenceInDays(cc1, cc2);
////        cc2.minusDays(100);
//        System.out.println("cc2.getTimeMillis(): " + cc2.getTimeMillis() / (1000*60*60*24));
//        cc2.set(1845, 10);
//        System.out.println("cc2.getTimeMillis(): " +  cc2);

/*        “2023-02-02” - ініціалізує як 2023 2 лютого 0 год, 0 хв, 0 сек, 0 мілісекунд
“2023-02-02 22:09” - ініціалізує як 2023 2 лютого 22 год, 9 хв, 0 сек, 0 мілісекунд
“2023-02-02 22:09:59” - ініціалізує як 2023 2 лютого 22 год, 9 хв, 59 сек, 0 мілісекунд
“2023-02-02 22:09:59 999” - ініціалізує як 2023 2 лютого 22 год, 9 хв, 59 сек, 999 мілісекунд*/

    }
}