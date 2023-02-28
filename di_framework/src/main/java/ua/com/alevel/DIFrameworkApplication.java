package ua.com.alevel;

public class DIFrameworkApplication {
    public static void start(Class<?> mainClass) {
        DIFrameworkContext context = new DIFrameworkContext(mainClass);
    }
}
