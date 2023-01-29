package ua.com.alevel.controller;

import ua.com.alevel.service.TextStatisticsMethods;
import ua.com.alevel.utils.ColorUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StatisticsController {

    public void start() {
        showRules();
        try {
            getResult();
        } catch (IOException e) {
            System.out.println(ColorUtils.getRedText().format("Oops...something went wrong:( Please try again later."));
        }
    }

    public void showRules() {
        System.out.println(ColorUtils.getReverse().format("WELCOME TO THE APPLICATION 'MINIMAL TEXT STATISTICS'"));
        System.out.println();
        System.out.println(ColorUtils.getBlueText().format("==> You will be prompted to enter text below."));
        System.out.println(ColorUtils.getBlueText().format("==> Based on the entered text, you will receive information about the rating, quantity and percentage of occurrences in sentences of each word."));
        System.out.println(ColorUtils.getBlueText().format("==> If you want to stop application, enter '0'"));
    }

    public void getResult() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text;
        while (true) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter your text:"));
            text = reader.readLine();
            if (text.equals("0")) {
                System.out.println();
                System.out.println(ColorUtils.getYellowText().format("The application is finished. Good Bye!"));
                System.out.println();
               break;
            }
            if (text.equals("")) {
                System.out.println(ColorUtils.getRedText().format("You have not entered anything."));
            } else {
                TextStatisticsMethods.printTable(TextStatisticsMethods.getWordStatisticsList(text));
            }
//            TextStatisticsMethods.printTable(TextStatisticsMethods.getWordStatisticsList(text));
        }
    }
}
