package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task_1 {

private static String condition = "Task1\n" + "Реализуйте задачу, которая принимает строку с консоли и вычленяет все числа и находит их сумму.\n";

    public void printCondition() {
        System.out.println(condition);
    }

    public ArrayList extractAllNumbers(String string) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(string);

        ArrayList<String> numList = new ArrayList();

        while (m.find()) {
            numList.add(m.group());
        }

        return numList;
    }

    public int sumAllNumbers(ArrayList<String> numList) {
        int sum = 0;

        for (int i = 0; i < numList.size(); i++) {
            int currentNum = Integer.parseInt(numList.get(i));
            sum += currentNum;

        }

        return sum;
    }

    public int findSum(String enteredString) {
        ArrayList<String> numList = extractAllNumbers(enteredString);
        int sum = sumAllNumbers(numList);

        return sum;
    }

    public void start() throws IOException {
        printCondition();

        System.out.println("Please enter your string:");

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String enteredString = reader.readLine();

            if (enteredString == null || enteredString.equals("")) {
                System.out.println("Activate program exit. Good bye!");
                break;
            }

            System.out.println(findSum(enteredString));
            System.out.println("\nIf you want to repeat, please write next. If not, please press Enter on the empty string.");

        }
    }

    public static String getCondition() {
        return condition;
    }

    public static void setCondition(String condition) {
        Task_1.condition = condition;
    }
}
