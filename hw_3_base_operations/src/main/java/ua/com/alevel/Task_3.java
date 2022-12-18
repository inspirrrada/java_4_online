package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task_3 {

private static String condition = "\n\n-----------------------Task_3-----------------------\n" + "В некоторой школе занятия начинаются в 9:00. Продолжительность урока — 45 минут, после 1-го, 3-го, 5-го и т.д. уроков перемена 5 минут, а после 2-го, 4-го, 6-го и т.д. — 15 минут. \nОпределите, когда заканчивается указанный урок. Данные вводятся с консоли. Входные данные: дан номер урока (число от 1 до 10).\n";


    public void printCondition() {
        System.out.println(condition);
    }

    public int getTotalMillis(int lessonNr) {
        int totalMillis = 0;

        int startLessons = 9 * 60 * 60 * 1000; //32 400 000 millis
        int oneLessonDuration = (45 * 60 * 1000); // 2 700 000 millis
        int break_5 = 5 * 60 * 1000; // 300 000 millis
        int break_15 = 15 * 60 * 1000; // 900 000 millis

        int breakDuration = ( lessonNr / 2 * break_5 ) + ( (lessonNr - 1) / 2 * break_15 );
        int finishedLessonsDuration = lessonNr * oneLessonDuration;

        totalMillis = startLessons + finishedLessonsDuration + breakDuration;

        return totalMillis;
    }

    public int getHours(int totalMillis) {
        int hours = (totalMillis / (1000 * 60 * 60)) % 24;

        return hours;
    }

    public int getMinutes(int totalMillis) {
        int minutes = (totalMillis / (1000 * 60)) % 60;

        return minutes;
    }

    public String getFinishTime(int lessonNr) {
        String finishTime = "";

        int totalMillis = getTotalMillis(lessonNr);
        int hours = getHours(totalMillis);
        int minutes = getMinutes(totalMillis);

        finishTime += hours + " " + minutes;

        return finishTime;
    }

    public void start() throws IOException {
        printCondition();

        System.out.println("Please enter number of lesson from 1 to 10:");

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int lessonNr = 0;

            String enteredString = reader.readLine();

            if (enteredString == null || enteredString.equals("")) {
                System.out.println("Task_3. Activate program exit. Good bye!");
                break;
            } else {
                //if user entered string, not number
                if (enteredString.matches(".*[a-zA-Z].*")) {
                    System.out.println("Error! Please enter number, not string!");
                } else {
                    lessonNr = Integer.parseInt(enteredString);

                    //if user entered num less or more than in the task condition
                    if (lessonNr < 1 || lessonNr > 10) {
                        System.out.println("Please be carefull, you should enter number only from 1 to 10. Try again");
                    } else {
                        System.out.println(getFinishTime(lessonNr));
                        System.out.println("\nIf you want to repeat, please write next. If not, please press Enter on the empty string.");
                    }
                }
            }
        }
    }

    public static String getCondition() {
        return condition;
    }

    public static void setCondition(String condition) {
        Task_3.condition = condition;
    }
}
