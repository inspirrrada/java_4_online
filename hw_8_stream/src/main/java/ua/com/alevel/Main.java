package ua.com.alevel;

import ua.com.alevel.controller.StatisticsController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new StatisticsController().start();
    }
}