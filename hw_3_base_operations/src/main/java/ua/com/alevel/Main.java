package ua.com.alevel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Task_1 task_1 = new Task_1();
        task_1.start();

        Task_2 task_2 = new Task_2();
        task_2.start();

        Task_3 task_3 = new Task_3();
        task_3.start();
    }
}
