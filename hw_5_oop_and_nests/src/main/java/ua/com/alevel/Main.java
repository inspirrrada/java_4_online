package ua.com.alevel;

import ua.com.alevel.controller.GamePlayController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GamePlayController gamePlayController = new GamePlayController();
        gamePlayController.start();
    }
}
