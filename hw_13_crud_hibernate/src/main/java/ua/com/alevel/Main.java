package ua.com.alevel;

import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.controller.GamePlayControllerImpl;

public class Main {
    public static void main(String[] args) {

        //HibernateConfig config = HibernateConfig.getInstance();
        new GamePlayControllerImpl().start();
//        DIFrameworkApplication.start(Main.class);
    }
}