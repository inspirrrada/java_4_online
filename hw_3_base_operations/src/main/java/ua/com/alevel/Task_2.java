package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task_2 {

private static String condition = "\n\n-----------------------Task_2-----------------------\n" + "Реализуйте задачу, которая принимает строку с консоли и вычленяет все символы латиницы/кириллицы и сортирует их, указывая количество вхождений каждого символа.\n";

    public void printCondition() {
        System.out.println(condition);
    }

    public char[] extractAllLetters(String string) {
        Pattern p = Pattern.compile("[a-z\\u0400-\\u04FF]");
        Matcher m = p.matcher(string);

        String allLetters = "";

        while (m.find()) {
            allLetters += m.group();
        }

        char[] generalCharArray = allLetters.toCharArray();

        return generalCharArray;
    }

    public char[] getUniqueCharArray(char[] generalCharArray) {
        String uniqueChars = "";

        for (int i = 0; i < generalCharArray.length; i++) {
            char currentChar = generalCharArray[i];

            if ( !uniqueChars.contains("" + currentChar) ) {
                uniqueChars += currentChar;
            }
        }

        char[] uniqueCharArray = uniqueChars.toCharArray();

        return uniqueCharArray;
    }

    public int[] getRepeatEveryChar(char[] generalCharArray, char[] uniqueCharArray) {
        int[] numArray = new int[uniqueCharArray.length];

        for (int i = 0; i < uniqueCharArray.length; i++) {
            int currentChar = uniqueCharArray[i];
            int count = 0;

            for (int j = 0; j < generalCharArray.length; j++) {
                int compareChar = generalCharArray[j];

                if (currentChar == compareChar) {
                    count++;
                }
            }

            numArray[i] = count;

        }

        return numArray;
    }

    public void sortArrays(int[] numArray, char[] charArray) {
        for (int i = 0; i < numArray.length; i++) {

            for (int j = i+1; j < numArray.length; j++) {
                int tempNum = 0;
                char tempChar = 0;

                if (numArray[i] < numArray[j]) {
                    tempNum = numArray[i];
                    numArray[i] = numArray[j];
                    numArray[j] = tempNum;

                    tempChar = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = tempChar;
                }
            }
        }
    }

    public String findResult(String enteredString) {
        String result = "";

        char[] generalCharArray = extractAllLetters(enteredString);
        char[] uniqueCharArray = getUniqueCharArray(generalCharArray);
        int[] repeatQtyArray = getRepeatEveryChar(generalCharArray, uniqueCharArray);
        sortArrays(repeatQtyArray, uniqueCharArray);

        for (int i = 0; i < uniqueCharArray.length; i++) {
            result += (i+1) + ". " + uniqueCharArray[i] + " - " + repeatQtyArray[i] + "\n";
        }

        return result;
    }

    public void start() throws IOException {
        printCondition();

        System.out.println("Please enter your string:");

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String enteredString = reader.readLine();

            if (enteredString == null || enteredString.equals("")) {
                System.out.println("Task_2. Activate program exit. Good bye!");
                break;
            }

            System.out.println(findResult(enteredString));
            System.out.println("\nIf you want to repeat, please write next. If not, please press Enter on the empty string.");

        }
    }

    public static String getCondition() {
        return condition;
    }

    public static void setCondition(String condition) {
        Task_2.condition = condition;
    }
}
